import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        LennardJones test = new LennardJones(40000);

        ArrayList<Double> energyArray = new ArrayList<>();
        ArrayList<Double> potentialArray = new ArrayList<>();
        ArrayList<Double> kineticArray = new ArrayList<>();
        double energy = test.getTotalEnergy();
        double pEnergy = test.getTotalPotentialEnergy();
        double kEnergy = test.getTotalKineticEnergy();

        energyArray.add(energy);
        potentialArray.add(pEnergy);
        kineticArray.add(kEnergy);

        JFrame animFrame = new JFrame();
        animFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        animFrame.setSize(250, 250);

        AnimationPanel animPanel = new AnimationPanel(test.getBoxLength());
        animFrame.add(animPanel);

        ArrayList<ArrayList<Double>> currentPositions = test.getPositions();
        animPanel.updatePanel(currentPositions);
        animFrame.setVisible(true);

        while (test.getCurrentTime() < test.getTotalTime()) {
            test.step();

            currentPositions = test.getPositions();
            animPanel.updatePanel(currentPositions);

            energy = test.getTotalEnergy();
            pEnergy = test.getTotalPotentialEnergy();
            kEnergy = test.getTotalKineticEnergy();
            energyArray.add(energy);
            potentialArray.add(pEnergy);
            kineticArray.add(kEnergy);
        }
        System.out.println("done");

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
        System.out.println("actually done");
        }
    }
