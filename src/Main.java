import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        LennardJones verlet = new LennardJones(1250000, 12, 150.);

        ArrayList<Double> energyArray = new ArrayList<>();
        ArrayList<Double> potentialArray = new ArrayList<>();
        ArrayList<Double> kineticArray = new ArrayList<>();
        double energy = verlet.getTotalEnergy();
        double pEnergy = verlet.getTotalPotentialEnergy();
        double kEnergy = verlet.getTotalKineticEnergy();

        energyArray.add(energy);
        potentialArray.add(pEnergy);
        kineticArray.add(kEnergy);

        JFrame animFrame = new JFrame();
        animFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        animFrame.setSize(250, 250); // slightly larger than panel

        AnimationPanel animPanel = new AnimationPanel(verlet.getBoxLength());
        animFrame.add(animPanel);

        ArrayList<ArrayList<Double>> currentPositions = verlet.getPositions();
        animPanel.updatePanel(currentPositions);
        animFrame.setVisible(true);

        while (verlet.getCurrentTime() < verlet.getTotalTime()) {
            verlet.step();

            currentPositions = verlet.getPositions();
            animPanel.updatePanel(currentPositions);

            energy = verlet.getTotalEnergy();
            pEnergy = verlet.getTotalPotentialEnergy();
            kEnergy = verlet.getTotalKineticEnergy();
            energyArray.add(energy);
            potentialArray.add(pEnergy);
            kineticArray.add(kEnergy);
        }
        System.out.println("writing...");

        // storing energy values in .csv for plotting in python
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
        System.out.println("Complete");
        }
    }
