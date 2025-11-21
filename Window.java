/*
    INTERFACE MADE BY:
    SIDNEI CORREIA JUNIOR [2510102122]
*/ 


import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Elements{
    private JPanel panel;

    public Window(){
        super();
        configurarJanela();
        bg_Panel();
        repaint();
    }
    
    private void configurarJanela(){

        getContentPane().setBackground(new Color(250,250,250));
        setTitle("Corre Cabrito");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

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

    private void bg_Panel(){
        panel = new JPanel();

        panel.setBounds(0,0,900,600);
        panel.setLayout(null);
        
        panel.add(Elements.line1);
        panel.add(Elements.line2);
        panel.add(Elements.line3);
        panel.add(Elements.line4);
        panel.add(Elements.line5);
        panel.add(Elements.line6);
        panel.add(Elements.line7);
        panel.add(Elements.line8);

        Elements.circle1.setName("c1");
        Elements.circle2.setName("c6");
        Elements.circle3.setName("c2");
        Elements.circle4.setName("c5");
        Elements.circle5.setName("c4");
        Elements.circle6.setName("c3");

        ((Circle) Elements.circle1).setLetter("C");
        ((Circle) Elements.circle4).setLetter("A");

        panel.add(Elements.circle1);
        panel.add(Elements.circle2);
        panel.add(Elements.circle3);
        panel.add(Elements.circle4);
        panel.add(Elements.circle5);
        panel.add(Elements.circle6);
        
        Elements.circle1.addMouseListener(Logic.Clicked( Elements.circle1));
        Elements.circle2.addMouseListener(Logic.Clicked( Elements.circle2));
        Elements.circle3.addMouseListener(Logic.Clicked( Elements.circle3));
        Elements.circle4.addMouseListener(Logic.Clicked( Elements.circle4));
        Elements.circle5.addMouseListener(Logic.Clicked( Elements.circle5));
        Elements.circle6.addMouseListener(Logic.Clicked( Elements.circle6));

        panel.setVisible(true);
        add(panel);
    }
}
