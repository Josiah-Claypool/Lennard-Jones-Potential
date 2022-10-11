import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimationPanel extends JPanel {
    Image blueSquare;
    ArrayList<ArrayList<Double>> currentPositions;
    int dimensions;

    AnimationPanel(double boxLength) {
        dimensions = (int) boxLength;
        this.setPreferredSize(new Dimension(dimensions, dimensions));
        this.setBackground(Color.white);
        blueSquare = new ImageIcon("blsq.png").getImage();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        for (ArrayList<Double> currentPosition : currentPositions) {
            int xPosition = currentPosition.get(0).intValue();
            int yPosition = currentPosition.get(1).intValue();
            int xAdjPosition = dimensions - xPosition;
            int yAdjPosition = dimensions - yPosition;
            g2D.drawImage(blueSquare, xAdjPosition, yAdjPosition, null);
        }
    }

    public void updatePanel(ArrayList<ArrayList<Double>> newPositions) {
        currentPositions = newPositions;
        repaint();
    }
}
