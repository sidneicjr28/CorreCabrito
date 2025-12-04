import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Circle extends JComponent {
    
    private Color circleColor = Color.BLACK;
    private String letter;
    private BufferedImage backgroundImage1;
    private BufferedImage backgroundImage2;


    // each circle is represented by letter each "C" for Cabrito or "A" to Caracará
    // empity to undefined

    // create circle with it's position on parameter with height and width defined to 81
    protected Circle(int x, int y){
        setBounds(x, y, 81, 81); // Set bounds to contain the 80x80 circle + stroke
        letter = " ";
        try {
            backgroundImage1 = ImageIO.read(new File("images/0.png"));
            backgroundImage2 = ImageIO.read(new File("images/1.png"));

        } catch (IOException e) {
            System.err.print("Error trying to get the image file.");
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Fill the circle with yellow color when the circle is active
        if (circleColor != Color.BLACK){
            g2.setColor(circleColor);
            g2.fillOval(0, 0, 80, 80);
        }
        
        g2.setColor(circleColor);
        g2.setStroke(new BasicStroke(3.0f));
        g2.drawOval(0, 0, 80, 80);

        // if the circle represents a Caracará (A) will change the image
        // if it represents a Cabrito (C) will also change the image
        // if none, will do nothing (without image and color) 
        if (backgroundImage1 != null && backgroundImage2 != null){
            if (letter.equals("A")){
                g2.drawImage(backgroundImage1, 3, 3, (getWidth() - 10), (getHeight() - 10), null);
            } else if(letter.equals("C")){
                g2.drawImage(backgroundImage2, 3, 3, (getWidth() - 10), (getHeight() - 10), null);
            }
        }
    }

    protected void changeColor(Color newColor) {
        this.circleColor = newColor;
        repaint();
    }

    protected String getLetter(){
    return this.letter;
    }

    protected Color getColor(){
        return this.circleColor;
    }

    protected void setLetter(String letter){
        this.letter = letter;
    }
}