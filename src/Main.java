public class Main {

    public static void main(String[] args) {
        LennardJones test = new LennardJones(1000);
        while (test.getCurrentTime() < 5000000) {
            test.step();
            if (test.currentTime % 1000000 == 0) {
                System.out.println(test.getPositions());
                System.out.println("total energy: " + test.getTotalEnergy());
                System.out.println("poten energy: " + test.getTotalPotentialEnergy());
                System.out.println("kinet energy: " + test.getTotalKineticEnergy());
            }
        }
        }
    }
