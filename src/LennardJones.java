import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LennardJones{
   double epsilon =  4 * 0.0004; // take note of factor of 4, atomic units
   double sigma = 6; // atomic units
   int x = 0; // to make index clearer
   int y = 1; // // to make index clearer
   int numOfObjects; // for testing will be 2, make dynamic later
   double totalTime;
   double boxLength; // for testing will be 5, make dynamic later\
   double dt = 0.5;
   ArrayList<ArrayList<Double>> positions = new ArrayList<>() ; // for testing will be a based of a boxLength of 5, still make
   ArrayList<Double> masses = new ArrayList<>();
   ArrayList<ArrayList<Double>> velocities = new ArrayList<>();
   double currentTime = 0;
   ArrayList<ArrayList<Double>> mainForces;
   double totalEnergy;
   double totalKineticEnergy;
   double totalPotentialEnergy;


   public LennardJones(int totalTime, int numOfObjects, double boxLength) {
      //this.numOfObjects = numOfObjects;
      this.totalTime = totalTime;
      this.numOfObjects = numOfObjects;
      this.boxLength = boxLength;
      for (int i = 0; i < numOfObjects; i++) {
         masses.add(73000.0);
      }
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

   public ArrayList<ArrayList<Double>> getVelocities() {
      return velocities;
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

   public double potential(double distance) {
      return epsilon * (Math.pow((sigma/distance), 12) - Math.pow((sigma/distance), 6));
   }

   public double force(double distance) {
      return epsilon * 6 / distance * ( 2 * Math.pow((sigma / distance), 12) - Math.pow((sigma / distance), 6));
   }

   public double calcKinetic(ArrayList<ArrayList<Double>> velocities) {
      double kineticEnergy = 0;
      for (int i = 0; i < masses.size(); i++) {
         kineticEnergy += 0.5 * masses.get(i) * ( Math.pow(velocities.get(i).get(x), 2) + Math.pow(velocities.get(i).get(y), 2));
      }
      return kineticEnergy;
   }

   public double calcPotential() {
      double potentialEnergy = 0;
      for (int i = 0; i < numOfObjects; i++) {
         for (int j = i + 1; j < numOfObjects; j++) {
            // replace with distance method later
            double r =  Math.sqrt( Math.pow( (positions.get(i).get(x) - positions.get(j).get(x)), 2 )  +
                    Math.pow( (positions.get(i).get(y) - positions.get(j).get(y)), 2 ));
            potentialEnergy += potential(r);
         }
      }
      return potentialEnergy;
   }

   public ArrayList<ArrayList<Double>> calcForces() {
      ArrayList<ArrayList<Double>> forces = new ArrayList<>();
      for (int i = 0; i < numOfObjects; i++) {
         ArrayList<Double> zeroes = new ArrayList<>(Arrays.asList(0., 0.));
         forces.add(zeroes);
      }
      for (int i = 0; i < numOfObjects; i++) {
         for (int j = i + 1; j < numOfObjects; j++) {
            double dx = positions.get(j).get(x) - positions.get(i).get(x);
            double dy = positions.get(j).get(y) - positions.get(i).get(y);
            double distance = Math.sqrt( Math.pow( (positions.get(i).get(x) - positions.get(j).get(x)), 2 )  +
                    Math.pow( (positions.get(i).get(y) - positions.get(j).get(y)), 2 ));
            double fx = force(distance) * dx / distance;
            double fy = force(distance) * dy / distance;
            forces.get(i).set(x, forces.get(i).get(x) - fx);
            forces.get(j).set(x, forces.get(j).get(x) + fx);
            forces.get(i).set(y, forces.get(i).get(y) - fy);
            forces.get(j).set(y, forces.get(j).get(y) + fy);
         }
      }
      return forces;
   }


   public void updatePositions(ArrayList<ArrayList<Double>> velocities, ArrayList<ArrayList<Double>> forces) {
      for (int i = 0; i < numOfObjects; i++) {
         positions.get(i).set(x, positions.get(i).get(x) + velocities.get(i).get(x) * dt + 0.5 *
                 (forces.get(i).get(x) / masses.get(i)) * Math.pow(dt, 2) );
         positions.get(i).set(y, positions.get(i).get(y) + velocities.get(i).get(y) * dt + 0.5 *
                 (forces.get(i).get(y) / masses.get(i)) * Math.pow(dt, 2) );
      }
   }

   public void updateVelocities(ArrayList<ArrayList<Double>> forces, ArrayList<ArrayList<Double>> nextForces) {
      for (int i = 0; i < numOfObjects; i++) {
         velocities.get(i).set(x, velocities.get(i).get(x) + 0.5 * (forces.get(i).get(x) + nextForces.get(i).get(x)) / masses.get(i) * dt);
         velocities.get(i).set(y, velocities.get(i).get(y) + 0.5 * (forces.get(i).get(y) + nextForces.get(i).get(y)) / masses.get(i) * dt);
      }
   }

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
