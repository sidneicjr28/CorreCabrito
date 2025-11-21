/*
    INTERFACE MADE BY:
    SIDNEI CORREIA JUNIOR [2510102122]
*/ 

import javax.swing.*;
import java.awt.*;

class Circle extends JComponent {

    private Color circleColor = Color.BLACK;
    private String letter;


    protected Circle(int x, int y){
        setBounds(x, y, 81, 81); // Set bounds to contain the 80x80 circle + stroke
        letter = " ";
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // AI to undestand how to create geometric elements using Graphics2D
        g2.setColor(circleColor);
        g2.setStroke(new BasicStroke(3.0f));
        g2.drawOval(0, 0, 80, 80);
        // END AI

        // Set the font and color for the letter
        // AI to understand how to put letters inside the circles
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        // END AI

        // Calculate the position to center the letter inside the circle
        FontMetrics metrics = g2.getFontMetrics(font);
        int x = (80 - metrics.stringWidth(this.letter)) / 2;
        int y = ((80 - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.drawString(this.letter, x, y);
    }

    protected void changeColor(Color newColor) {
        this.circleColor = newColor;
        repaint();
    }

    protected Color getColor(){
        return this.circleColor;
    }

    protected void setLetter(String letter){
        this.letter = letter;
    }
}