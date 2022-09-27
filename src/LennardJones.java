import java.util.ArrayList;

public class LennardJones {
   double epsilon =  4 * 0.0004; // take note of factor of 4, atomic units
   double sigma = 6; // atomic units
   int x = 0; // to make index clearer
   int y = 0; // // to make index clearer


   public LennardJones() {

   }

   public double potential(double distance) {
      return epsilon * (Math.pow((sigma/distance), 12) - Math.pow((sigma/distance), 6));
   }

   public double force(double distance) {
      return epsilon * 6 / distance * (Math.pow((2 * (sigma / distance)), 12) - Math.pow((sigma / distance), 6));
   }

   public double calcKinetic(ArrayList<Double> masses, ArrayList<ArrayList<Double>> velocities) {
      double kineticEnergy = 0;
      for (int i = 0; i < masses.size(); i++) {
         kineticEnergy += 0.5 * masses.get(i) * (( Math.pow(velocities.get(i).get(x), 2) + Math.pow(velocities.get(i).get(y), 2)));
      }
      return kineticEnergy;
   }
}
