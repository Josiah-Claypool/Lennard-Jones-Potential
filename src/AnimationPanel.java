import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimationPanel extends JPanel {
    Image blueSquare;
    ArrayList<ArrayList<Double>> currentPositions;
    int dimensions;

    /**
     * Initializes the JPanel for animation
     * @param boxLength   The confines of the box
     */
    AnimationPanel(double boxLength) {
        dimensions = (int) boxLength;
        this.setPreferredSize(new Dimension(dimensions, dimensions));
        this.setBackground(Color.white);
        blueSquare = new ImageIcon("blsq.png").getImage();
    }

    /**
     * Displays the molecules on the JPanel represented by blue squares
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        for (ArrayList<Double> currentPosition : currentPositions) {
            int xPosition = currentPosition.get(0).intValue();
            int yPosition = currentPosition.get(1).intValue();
            // the origin for the JPanel is top left so adjust y-axis
            int yAdjPosition = dimensions - yPosition;
            g2D.drawImage(blueSquare, xPosition, yAdjPosition, null);
        }
    }

    /**
     * Repaints with new positions
     * @param newPositions   The updated positions
     */
    public void updatePanel(ArrayList<ArrayList<Double>> newPositions) {
        currentPositions = newPositions;
        repaint();
    }
}
