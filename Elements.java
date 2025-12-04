import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

// Interface that create elements to be used throught logic and graphical interface
public interface Elements {
    JPanel status = new JPanel();
    JLabel plays = new JLabel();
    JLabel player = new JLabel();

    JComponent circle1 = new Circle(400,50);
    JComponent circle2 = new Circle(400,250);
    JComponent circle3 = new Circle(600,160);
    JComponent circle4 = new Circle(200,160);
    JComponent circle5 = new Circle(300,450);
    JComponent circle6 = new Circle(500,450);

    JComponent line1 = new Line(405, 109, 275, 181); 
    JComponent line2 = new Line(475, 109, 605, 181); 
    JComponent line3 = new Line(440, 130, 440, 250); 
    JComponent line4 = new Line(422, 326, 358, 454);
    JComponent line5 = new Line(458, 326, 522, 454);
    JComponent line6 = new Line(252, 237, 328, 453); 
    JComponent line7 = new Line(628, 237, 552, 453); 
    JComponent line8 = new Line(380, 490, 500, 490);

    JMenuBar menu = new JMenuBar();

    JMenu menu_game = new JMenu("Game");
    JMenu menu_exit = new JMenu("Exit");


    JMenuItem menuItem_restart = new JMenuItem("Restart");
    JMenuItem menu_authors = new JMenuItem("Authors");
}
