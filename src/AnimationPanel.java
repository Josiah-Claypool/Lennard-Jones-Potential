import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {

    Image orangeSquare;
    Image blueSquare;
    double xPosition;
    double yPosition;
    double xPosition2;
    double yPosition2;

    AnimationPanel() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setBackground(Color.white);
        orangeSquare = new ImageIcon("orsq.png").getImage();
        blueSquare = new ImageIcon("blsq.png").getImage();

    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(orangeSquare, (int) xPosition, (int) yPosition, null);
        g2D.drawImage(blueSquare, (int) xPosition2, (int) yPosition2, null);
    }

    public void updatePanel(double newXPosition, double newYPosition, double newXPosition2, double newYPosition2) {
        // parameters to be replaced by arraylist in future
        xPosition = newXPosition;
        yPosition = newYPosition;
        xPosition2 = newXPosition2;
        yPosition2 = newYPosition2;
        repaint();
    }
}
