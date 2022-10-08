import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        LennardJones test = new LennardJones(1000);

        ArrayList<Double> energyArray = new ArrayList<>();
        ArrayList<Double> potentialArray = new ArrayList<>();
        ArrayList<Double> kineticArray = new ArrayList<>();
        double energy = test.getTotalEnergy();
        double pEnergy = test.getTotalPotentialEnergy();
        double kEnergy = test.getTotalKineticEnergy();

        energyArray.add(energy);
        potentialArray.add(pEnergy);
        kineticArray.add(kEnergy);

        while (test.getCurrentTime() < 200000) {
            test.step();
            energy = test.getTotalEnergy();
            pEnergy = test.getTotalPotentialEnergy();
            kEnergy = test.getTotalKineticEnergy();
            energyArray.add(energy);
            potentialArray.add(pEnergy);
            kineticArray.add(kEnergy);
        }

        PrintWriter writer = new PrintWriter("lennard.csv");
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < energyArray.size(); i++) {
            row.add(String.valueOf(energyArray.get(i)));
            row.add(String.valueOf(potentialArray.get(i)));
            row.add(String.valueOf(kineticArray.get(i)));
            writer.println(String.join(",", row));
            row.clear();
        }
        writer.close();
        }
    }
