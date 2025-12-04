import javax.swing.*;
import java.awt.*;


// Class to create each line that will be connected to the circles
class Line extends JComponent {

    int x1;
    int y1;
    int x2;
    int y2;

    protected  Line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setBounds(0, 0, 900, 600);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3.0f));
        g2.drawLine(x1, y1, x2, y2);
    }
}