import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LennardJones{
   double epsilon =  0.0004; // atomic units
   double sigma = 6; // atomic units
   int x = 0; // to make index clearer
   int y = 1; // // to make index clearer
   int numOfObjects; // number of molecules
   double totalTime;
   double boxLength; // the confines of position
   double dt = 0.5; // time step
   ArrayList<ArrayList<Double>> positions = new ArrayList<>();
   ArrayList<Double> masses = new ArrayList<>();
   ArrayList<ArrayList<Double>> velocities = new ArrayList<>();
   double currentTime = 0;
   ArrayList<ArrayList<Double>> mainForces;
   double totalEnergy;
   double totalKineticEnergy;
   double totalPotentialEnergy;


   /**
    * Initializes the configuration by setting the number of molecules, the box dimensions, the total time of the run,
    * the masses of the objects, the random distribution of velocities, the distribution of positions, the initial
    * forces, initial potential energy, initial kinetic energy, and the initial total energy.
    *
    * @param totalTime   The total time desired for the run
    * @param numOfObjects   The desired amount of molecules (currently tested for up to a dozen)
    * @param boxLength   The dimensions of the box confines
    */
   public LennardJones(int totalTime, int numOfObjects, double boxLength) {
      //this.numOfObjects = numOfObjects;
      this.totalTime = totalTime;
      this.numOfObjects = numOfObjects;
      this.boxLength = boxLength;
      for (int i = 0; i < numOfObjects; i++) {
         masses.add(73000.0); // atomic units
      }
      // give random velocities within a range and 50/50 on negative or positive direction
      Random random = new Random();
      for (int i = 0; i < numOfObjects; i++) {
         ArrayList<Double> row = new ArrayList<>(Arrays.asList(0.001 * random.nextDouble(), 0.001 * random.nextDouble()));
         double probability = random.nextDouble();
         if (probability < 0.50) {
            row.set(x, -1.0 * row.get(x)); // could replace with ? maybe
         }
         probability = random.nextDouble();
         if (probability < 0.50) {
            row.set(y, -1.0 * row.get(y));
         }
         velocities.add(row);
      }
      // initial positions
      for (int i = 0; i < numOfObjects; i++) {
         if (i < 6) {
            ArrayList<Double> positionRow = new ArrayList<>(Arrays.asList((i + 1) * 20., 20.));
            positions.add(positionRow);
         }
         else {
            ArrayList<Double> positionRow = new ArrayList<>(Arrays.asList((i - 5) * 20., 40.));
            positions.add(positionRow);
         }
      }
      mainForces = calcForces();
      totalPotentialEnergy = calcPotential();
      totalKineticEnergy = calcKinetic(velocities);
      totalEnergy = totalKineticEnergy + totalPotentialEnergy;
   }

   public ArrayList<ArrayList<Double>> getPositions() {
      return positions;
   }

   public double getCurrentTime() { return currentTime; }

   public double getTotalEnergy() { return totalEnergy; }

   public double getTotalPotentialEnergy() { return totalPotentialEnergy; }
   public double getTotalKineticEnergy() { return totalKineticEnergy; }

   public double getBoxLength() { return boxLength; }

   public double getTotalTime() { return totalTime; }


   /**
    * Calculates and returns the potential energy of the Lennard-Jones potential according to the distance between
    * two molecules
    *
    * @param distance   The distance between the molecules (r)
    * @return   The potential energy
    */
   public double potential(double distance) {
      return 4 * epsilon * (Math.pow((sigma/distance), 12) - Math.pow((sigma/distance), 6));
   }

   /**
    * Calculates and returns the force from the Lennard-Jones potential according to the distance between
    * two molecules
    *
    * @param distance   The distance between the molecules (r)
    * @return   The force
    */
   public double force(double distance) {
      return 4 * epsilon * 6 / distance * ( 2 * Math.pow((sigma / distance), 12) - Math.pow((sigma / distance), 6));
   }

   /**
    * Calculates and returns the kinetic energy of the entire system from the motion of all the molecules
    *
    * @param velocities   The current velocities of the configuration
    * @return   The total kinetic energy
    */
   public double calcKinetic(ArrayList<ArrayList<Double>> velocities) {
      double kineticEnergy = 0;
      for (int i = 0; i < masses.size(); i++) {
         kineticEnergy += 0.5 * masses.get(i) * ( Math.pow(velocities.get(i).get(x), 2) + Math.pow(velocities.get(i).get(y), 2));
      }
      return kineticEnergy;
   }

   /**
    * Calculates and returns the potential energy of the entire system from each pair interaction
    *
    * @return   The total potential energy
    */
   public double calcPotential() {
      double potentialEnergy = 0;
      for (int i = 0; i < numOfObjects; i++) {
         for (int j = i + 1; j < numOfObjects; j++) {
            double r =  Math.sqrt( Math.pow( (positions.get(i).get(x) - positions.get(j).get(x)), 2 )  +
                    Math.pow( (positions.get(i).get(y) - positions.get(j).get(y)), 2 ));
            potentialEnergy += potential(r);
         }
      }
      return potentialEnergy;
   }

   /**
    * Calculates and returns the forces on each molecule according to each pair interaction
    *
    * @return  The forces
    */
   public ArrayList<ArrayList<Double>> calcForces() {
      ArrayList<ArrayList<Double>> forces = new ArrayList<>();
      for (int i = 0; i < numOfObjects; i++) {
         ArrayList<Double> zeroes = new ArrayList<>(Arrays.asList(0., 0.));
         forces.add(zeroes);
      }
      for (int i = 0; i < numOfObjects; i++) {
         for (int j = i + 1; j < numOfObjects; j++) {
            double dx = positions.get(j).get(x) - positions.get(i).get(x); // distance in x
            double dy = positions.get(j).get(y) - positions.get(i).get(y); // distance in y
            double distance = Math.sqrt( Math.pow( (positions.get(i).get(x) - positions.get(j).get(x)), 2 )  +
                    Math.pow( (positions.get(i).get(y) - positions.get(j).get(y)), 2 )); // radius distance
            double fx = force(distance) * dx / distance; // x component of forces
            double fy = force(distance) * dy / distance; // y component of forces
            forces.get(i).set(x, forces.get(i).get(x) - fx);
            forces.get(j).set(x, forces.get(j).get(x) + fx);
            forces.get(i).set(y, forces.get(i).get(y) - fy);
            forces.get(j).set(y, forces.get(j).get(y) + fy);
         }
      }
      return forces;
   }


   /**
    * Updates the positions of every molecule
    *
    * @param velocities   The current velocities
    * @param forces   The current forces
    */
   public void updatePositions(ArrayList<ArrayList<Double>> velocities, ArrayList<ArrayList<Double>> forces) {
      for (int i = 0; i < numOfObjects; i++) {
         positions.get(i).set(x, positions.get(i).get(x) + velocities.get(i).get(x) * dt + 0.5 *
                 (forces.get(i).get(x) / masses.get(i)) * Math.pow(dt, 2) );
         positions.get(i).set(y, positions.get(i).get(y) + velocities.get(i).get(y) * dt + 0.5 *
                 (forces.get(i).get(y) / masses.get(i)) * Math.pow(dt, 2) );
      }
   }

   /**
    * Updates the velocities of every molecule
    *
    * @param forces   The current forces
    * @param nextForces   The forces from one step ahead
    */
   public void updateVelocities(ArrayList<ArrayList<Double>> forces, ArrayList<ArrayList<Double>> nextForces) {
      for (int i = 0; i < numOfObjects; i++) {
         velocities.get(i).set(x, velocities.get(i).get(x) + 0.5 * (forces.get(i).get(x) + nextForces.get(i).get(x)) / masses.get(i) * dt);
         velocities.get(i).set(y, velocities.get(i).get(y) + 0.5 * (forces.get(i).get(y) + nextForces.get(i).get(y)) / masses.get(i) * dt);
      }
   }


   /**
    * Takes a step forward in time. Updates the positions according to the current velocities and forces. Checks that
    * no molecules are outside of bounds and adjust position if so. Updates the forces based off the new positions.
    * Updates the velocities from the current forces and the new forces. Changes the current forces to be the new forces.
    * Calculates the potential energy, kinetic energy, and total energy.
    *
    */
   public void step() {
      currentTime += dt;
      updatePositions(velocities, mainForces);
      // handling boundaries
      for (int i = 0; i < numOfObjects; i++) {
         if (positions.get(i).get(x) > boxLength) {
            positions.get(i).set(x, boxLength);
            velocities.get(i).set(x, -1 * velocities.get(i).get(x));
         }
         if (positions.get(i).get(x) < 0) {
            positions.get(i).set(x, 0.);
            velocities.get(i).set(x, -1 * velocities.get(i).get(x));
         }
         if (positions.get(i).get(y) > boxLength) {
            positions.get(i).set(y, boxLength);
            velocities.get(i).set(y, -1 * velocities.get(i).get(y));
         }
         if (positions.get(i).get(y) < 0) {
            positions.get(i).set(y, 0.);
            velocities.get(i).set(y, -1 * velocities.get(i).get(y));
         }
         ArrayList<ArrayList<Double>> updatedForces = calcForces();
         updateVelocities(mainForces, updatedForces);
         mainForces = DeepCopy.deepCopy2D(updatedForces);
         totalPotentialEnergy = calcPotential();
         totalKineticEnergy = calcKinetic(velocities);
         totalEnergy = totalKineticEnergy + totalPotentialEnergy;
      }
   }
}
