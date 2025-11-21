/*
    SIDNEI CORREIA JUNIOR [2510102122]

    LOGIC MADE BY:
    ADRIANO MOISES []
*/

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public abstract class Logic extends JFrame implements Elements{
 
    protected static MouseAdapter Clicked(JComponent component){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                // Serve para mostrar no terminal qual circulo foi clicado
                System.out.println("Component " + component.getName() + " has been clicked!");

                // Aqui tu pode mudar a cor e letra de um circulo específico
                // Circulos estão numerados de 1 a 6
                // Pode testar clicando neles como programa executando que aparece o valor no terminal.
                // Pode apagar isso depois quando entender bem
                ((Circle) Elements.circle1).changeColor(Color.GREEN);
                ((Circle) Elements.circle1).setLetter("X");

                // Aqui tem um if e else para identifcar a cor atual do circulo
                // Pode alterar tudo aqui e colocar tua lógica
                if (((Circle) component).getColor() == Color.RED){
                    ((Circle) component).changeColor(Color.BLACK);
                    ((Circle) component).setLetter("T");

                }else{
                    ((Circle) component).changeColor(Color.RED);
                    ((Circle) component).setLetter("S");
                }

            }
        };
    }

    protected static ActionListener restart_game(Window current_window){
        return e -> {
            current_window.repaint();

            ((Circle) Elements.circle1).changeColor(Color.BLACK);
            ((Circle) Elements.circle1).setLetter("C");

            ((Circle) Elements.circle2).changeColor(Color.BLACK);
            ((Circle) Elements.circle2).setLetter(" ");

            ((Circle) Elements.circle3).changeColor(Color.BLACK);
            ((Circle) Elements.circle3).setLetter(" ");

            ((Circle) Elements.circle4).changeColor(Color.BLACK);
            ((Circle) Elements.circle4).setLetter("A");

            ((Circle) Elements.circle5).changeColor(Color.BLACK);
            ((Circle) Elements.circle5).setLetter(" ");

            ((Circle) Elements.circle6).changeColor(Color.BLACK);
            ((Circle) Elements.circle6).setLetter(" ");
            
        };
    }

    protected static MouseAdapter exit_game(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("See ya!");
                System.exit(0); // Exit the application
            }
        };
    }

    protected static ActionListener show_authors(){
        return e ->{
            JOptionPane.showMessageDialog(null, "Jogo criado por uma das melhores duplas da ES!\n Sidnei Correia Junior!\n Adriano Moisés!");
        };
    }

}
