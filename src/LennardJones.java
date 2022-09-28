import java.util.ArrayList;

public class LennardJones {
   double epsilon =  4 * 0.0004; // take note of factor of 4, atomic units
   double sigma = 6; // atomic units
   int x = 0; // to make index clearer
   int y = 1; // // to make index clearer
   int numOfObjects = 2; // for testing will be 2, make dynamic later
   int totalTime;
   int boxLength = 5; // for testing will be 5, make dynamic later\
   double dt = 0.5;

   ArrayList<ArrayList<Double>> positions; // for testing will be a based of a boxLength of 10
   ArrayList<Double> masses;


   public LennardJones(int totalTime) {
      //this.numOfObjects = numOfObjects;
      //this.totalTime = totalTime;
   }

   public double potential(double distance) {
      return epsilon * (Math.pow((sigma/distance), 12) - Math.pow((sigma/distance), 6));
   }

   public double force(double distance) {
      return epsilon * 6 / distance * (Math.pow((2 * (sigma / distance)), 12) - Math.pow((sigma / distance), 6));
   }

   public double calcKinetic(ArrayList<ArrayList<Double>> velocities) {
      double kineticEnergy = 0;
      for (int i = 0; i < masses.size(); i++) {
         kineticEnergy += 0.5 * masses.get(i) * (( Math.pow(velocities.get(i).get(x), 2) + Math.pow(velocities.get(i).get(y), 2)));
      }
      return kineticEnergy;
   }

   public double calcPotential() {
      double potentialEnergy = 0;
      double r;
      for (int i = 0; i < numOfObjects; i++) {
         for (int j = 1; j < numOfObjects; j++) {
            r =  Math.sqrt( Math.pow( (positions.get(i).get(x) - positions.get(i).get(y)), 2 )  +
                    Math.pow( (positions.get(j).get(x) - positions.get(j).get(y)), 2 ));
            potentialEnergy += potential(r);
         }
      }
      return potentialEnergy;
   }

   public void updatePositions(ArrayList<ArrayList<Double>> velocities, ArrayList<ArrayList<Double>> forces) {
      for (int i = 0; i < numOfObjects; i++) {
         positions.get(i).set(x, positions.get(i).get(x) + velocities.get(i).get(x) * dt + 0.5 *
                 (forces.get(i).get(x) / masses.get(i)) * Math.pow(dt, 2) );
         positions.get(i).set(y, positions.get(i).get(y) + velocities.get(i).get(y) * dt + 0.5 *
                 (forces.get(i).get(y) / masses.get(i)) * Math.pow(dt, 2) );
      }
   }
}
