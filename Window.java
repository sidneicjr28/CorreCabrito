import javax.swing.*;
import java.awt.*;

// Interface Elements = interface that creates all important elements
public class Window extends JFrame implements Elements{
    private JPanel panel;

    // Initialize window calling the elements functions
    public Window(){
        super();
        configurarJanela();
        bg_Panel();
        repaint();
    }
    
    // Function to stylize and configure the main window
    private void configurarJanela(){

        getContentPane().setBackground(new Color(250,250,250));
        setTitle("Corre Cabrito");
        setSize(900,680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Add MenuBar to the window
        Elements.menu_game.add(Elements.menu_volume);
        Elements.menu_volume.addActionListener(Logic.volume_Control());
        Elements.menu_game.add(Elements.menuItem_restart);
        Elements.menu_game.add(Elements.menu_authors);
        Elements.menu_authors.addActionListener(Logic.show_authors());

        Elements.menu.add(Elements.menu_game);
        Elements.menuItem_restart.addActionListener(Logic.restart_game(this));
        
        Elements.menu.add(Elements.menu_exit);
        Elements.menu_exit.addMouseListener(Logic.exit_game());

        setJMenuBar(Elements.menu);

        setVisible(true);
    }

    // Configure the panel
    private void bg_Panel(){
        panel = new JPanel();

        panel.setBounds(0,0,900,700);
        panel.setLayout(null);

        // Add all the lines that will connect the circles
        panel.add(Elements.line1);
        panel.add(Elements.line2);
        panel.add(Elements.line3);
        panel.add(Elements.line4);
        panel.add(Elements.line5);
        panel.add(Elements.line6);
        panel.add(Elements.line7);
        panel.add(Elements.line8);

        // Naming the circles to include for the logic
        Elements.circle1.setName("c1");
        Elements.circle2.setName("c6");
        Elements.circle3.setName("c2");
        Elements.circle4.setName("c5");
        Elements.circle5.setName("c4");
        Elements.circle6.setName("c3");

        // Requirement to start the characters in these positions
        ((Circle) Elements.circle1).setLetter("C");
        ((Circle) Elements.circle4).setLetter("A");

        // Add the circles
        panel.add(Elements.circle1);
        panel.add(Elements.circle2);
        panel.add(Elements.circle3);
        panel.add(Elements.circle4);
        panel.add(Elements.circle5);
        panel.add(Elements.circle6);
        
        // Mouse event listener, whenever the user click each circle (Logic)
        Elements.circle1.addMouseListener(Logic.Clicked( Elements.circle1));
        Elements.circle2.addMouseListener(Logic.Clicked( Elements.circle2));
        Elements.circle3.addMouseListener(Logic.Clicked( Elements.circle3));
        Elements.circle4.addMouseListener(Logic.Clicked( Elements.circle4));
        Elements.circle5.addMouseListener(Logic.Clicked( Elements.circle5));
        Elements.circle6.addMouseListener(Logic.Clicked( Elements.circle6));

        panel.setVisible(true);
        add(panel);
        statusPanel();

        statusPanel();
    }

    // Create and stylize the status panel
    // Gray panel in the bottom of the main window
    public void statusPanel(){

        status.setBounds(0, 580, 900, 50);
        status.setBackground(new Color(55,55,55));
        status.setLayout(new BorderLayout(20, 0)); // Use BorderLayout with a horizontal gap
        status.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 80)); // Add padding 

        plays.setText("Rounds: 0");
        plays.setFont(new Font("Arial", Font.PLAIN, 22));
        plays.setForeground(Color.white);

        player.setText("Current player: Cabrito");
        player.setFont(new Font("Arial", Font.PLAIN, 22));
        player.setForeground(Color.white);



        status.add(plays, BorderLayout.WEST);
        status.add(player, BorderLayout.EAST);
        
        panel.add(status);
    }
}
