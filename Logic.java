/*
    (IN PORTUGUESE) PART OF THE LOGIC WAS MADE BY:
    ADRIANO MOISES
    + IMPLEMENTAÇÃO DA LÓGICA DE JOGO, EXCEÇÕES E CONTROLE DE ESTADO
*/

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Custom exception for invalid moves
class JogadaInvalidaException extends Exception {
    public JogadaInvalidaException(String message) {
        super(message);
    }
}

public abstract class Logic extends JFrame implements Elements{
 
    // Piece characters (Goat: C, Bird: A)
    private static final String charPecaCabrito = "C"; 
    private static final String charPecaCaracara = "A"; 
    
    private static Map<String, Set<String>> mapaAdjacencias; // Board graph (move validation)
    private static Map<String, JComponent> mapaComponentes; // Map circle names to objects
    
    private static String turnoAtual = charPecaCabrito; 
    private static JComponent pecaSelecionada = null; 
    private static int contadorJogadas = 0;
    private static boolean superPuloUsado = false; 
    private static boolean jogoEmAndamento = true;

    // Initialize the board graph and component mapping
    static {
        mapaAdjacencias = new HashMap<>();
        mapaAdjacencias.put("c1", Set.of("c5", "c2", "c6")); 
        mapaAdjacencias.put("c5", Set.of("c1", "c4", "c6")); 
        mapaAdjacencias.put("c2", Set.of("c1", "c3", "c6")); 
        mapaAdjacencias.put("c4", Set.of("c5", "c3", "c6")); 
        mapaAdjacencias.put("c3", Set.of("c2", "c4", "c6")); 
        mapaAdjacencias.put("c6", Set.of("c1", "c5", "c2", "c4", "c3")); // Center node
        
        mapaComponentes = new HashMap<>();
        mapaComponentes.put("c1", Elements.circle1);
        mapaComponentes.put("c6", Elements.circle2); 
        mapaComponentes.put("c2", Elements.circle3);
        mapaComponentes.put("c5", Elements.circle4);
        mapaComponentes.put("c4", Elements.circle5);
        mapaComponentes.put("c3", Elements.circle6);
    }

    private static String obterPecaNaPosicao(JComponent circle) {
        return ((Circle) circle).getLetter().trim();
    }

    private static void definirPecaNaPosicao(JComponent circle, String charPeca) {
        ((Circle) circle).setLetter(charPeca);
    }
    
    // Check if the Super Jump can be used (only once, destination must be empty)
    private static boolean podeUsarSuperPulo(JComponent destino) {
        if (superPuloUsado) return false;
        return obterPecaNaPosicao(destino).isEmpty();
    }

    // Main mouse click handler
    protected static MouseAdapter Clicked(JComponent component){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Main.playClick("songs/click.wav");

                if (!jogoEmAndamento) return;

                // 1. Piece selection (Origin)
                if (pecaSelecionada == null) {
                    String pecaNaPosicao = obterPecaNaPosicao(component);
                    
                    if (pecaNaPosicao.equals(turnoAtual)) {
                        pecaSelecionada = component;
                        ((Circle) pecaSelecionada).changeColor(Color.YELLOW);
                    } else if (!pecaNaPosicao.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Jogada Inválida: Não é seu turno ou você selecionou a peça errada. Turno: " + turnoAtual, "Erro de Turno", JOptionPane.ERROR_MESSAGE);
                    }

                    return;
                }

                // 2. Movement attempt (Destination)
                JComponent destino = component;
                
                try {
                    // moverPeca agora retorna true se houver captura
                    boolean capturou = moverPeca(pecaSelecionada, destino);
                    
                    // Successful move: reset selection
                    ((Circle) pecaSelecionada).changeColor(Color.BLACK); 
                    pecaSelecionada = null;
                    
                    // Check for game over (capture) - SÓ verifica se houve captura
                    if (capturou) {
                        verificarFimDeJogo();
                    }

                    // Switch turn
                    if (jogoEmAndamento) {
                        turnoAtual = (turnoAtual.equals(charPecaCabrito)) ? charPecaCaracara : charPecaCabrito;
                        contadorJogadas++;
                        plays.setText("Rounds: " + contadorJogadas);
                        System.out.println("Rounds: " + contadorJogadas + " | Next Turn: " + turnoAtual);
                       
                        if (contadorJogadas > 18  && ((Circle) circle2).getLetter().equals(" ")){
                            status.setBackground(new Color(255, 0, 0));
                            circle2.setSize(0,0);
                            line5.setVisible(false);
                            line4.setVisible(false);
                            line3.setVisible(false);
                        }
                       
                        if (turnoAtual.equals("C")){
                            player.setText("Current player: Cabrito");
                        }else if(turnoAtual.equals("A")){
                            player.setText("Current player: Carcará");
                        }
                    }
                    
                } catch (JogadaInvalidaException ex) {
                    Main.playClick("alert.wav");

                    JOptionPane.showMessageDialog(null, "Jogada Inválida: " + ex.getMessage(), "Erro de Jogo", JOptionPane.ERROR_MESSAGE);
                    
                    // Deselect piece if destination is the same as origin
                    if (destino == pecaSelecionada) {
                        ((Circle) pecaSelecionada).changeColor(Color.BLACK);
                        pecaSelecionada = null;
                    }
                }
            }
        };
    }

    // Core logic for movement validation (Adjacency and Super Jump)
    // Returns true if a capture occurred, false otherwise.
    private static boolean moverPeca(JComponent origem, JComponent destino) throws JogadaInvalidaException {
        String charPeca = obterPecaNaPosicao(origem);
        String nomeOrigem = origem.getName();
        String nomeDestino = destino.getName();
        String charPecaDestino = obterPecaNaPosicao(destino);
        if (!charPeca.equals(turnoAtual)) {
            throw new JogadaInvalidaException("Não é a sua peça para mover.");
        }
        
        // Handle capture rule (Goat cannot move to Bird's spot)
        if (charPeca.equals(charPecaCabrito) && charPecaDestino.equals(charPecaCaracara)) {
            throw new JogadaInvalidaException("O Cabrito não pode se mover para a posição do Carcará.");
        }
        
        // If destination is occupied by own piece (and not a capture spot for the Bird)
        if (!charPecaDestino.isEmpty() && !(charPeca.equals(charPecaCaracara) && charPecaDestino.equals(charPecaCabrito))) {
             throw new JogadaInvalidaException("O destino não está vazio.");
        }

        // 1. Normal Move Check (Adjacency)
        boolean isAdjacente = mapaAdjacencias.get(nomeOrigem).contains(nomeDestino);

        if (nomeOrigem.equals("c6") && nomeDestino.equals("c5") || nomeOrigem.equals("c5") && nomeDestino.equals("c6")){
            throw new JogadaInvalidaException("Destino inválido");
        }

        if (nomeOrigem.equals("c6") && nomeDestino.equals("c2") || nomeOrigem.equals("c2") && nomeDestino.equals("c6")){
            throw new JogadaInvalidaException("Destino inválido");
        }

        System.out.println("DEBUG ADJACÊNCIA: Movimento " + charPeca + " de " + nomeOrigem + " para " + nomeDestino + ". É adjacente? " + isAdjacente);

        if (isAdjacente) {
            return executarMovimento(origem, destino, charPeca); // Return capture status
        }

        // 2. Super Jump Check (Goat only)
        if (charPeca.equals(charPecaCabrito) && podeUsarSuperPulo(destino)) {
            boolean capturou = executarMovimento(origem, destino, charPeca);
            superPuloUsado = true; 
            JOptionPane.showMessageDialog(null, "Super Pulo do Cabrito usado!", "Habilidade Usada", JOptionPane.INFORMATION_MESSAGE);
            return capturou; // Return capture status
        }
        
        // Invalid move type
        throw new JogadaInvalidaException("O movimento não é adjacente e/ou não é um Super Pulo válido.");
    }

    // Executes the piece position swap and handles capture. Returns true if capture occurred.
    private static boolean executarMovimento(JComponent origem, JComponent destino, String charPeca) {
        String pecaDestinoAntes = obterPecaNaPosicao(destino);
        boolean capturou = false; 
        
        // 1. Clear the origin spot
        definirPecaNaPosicao(origem, " ");
        
        // 2. Place the piece at the destination, handling capture
        if (charPeca.equals(charPecaCaracara) && pecaDestinoAntes.equals(charPecaCabrito)) {
            // Cenário de Captura
            definirPecaNaPosicao(destino, charPecaCaracara);
            System.out.println("DEBUG CAPTURA: Carcará capturou o Cabrito em " + destino.getName());
            capturou = true; 
        } else {
            // Movimento normal
            definirPecaNaPosicao(destino, charPeca);
        }

        System.out.println("DEBUG ESTADO: Origem (" + origem.getName() + ") contém: '" + obterPecaNaPosicao(origem) + "' | Destino (" + destino.getName() + ") contém: '" + obterPecaNaPosicao(destino) + "'");
        
        ((Circle) destino).changeColor(Color.BLACK); 
        origem.repaint();
        destino.repaint();

        return capturou;
    }
    
    // Checks for the game over condition (Capture) - Now only displays message
    // É chamada APENAS se a captura foi confirmada em executarMovimento.
    private static void verificarFimDeJogo() {
        jogoEmAndamento = false;

        Main.playClick("songs/win.wav");
        
        JOptionPane.showMessageDialog(null, 
            "CABRITO CAPTURADO! O jogo terminou.\n" +
            "Total de jogadas realizadas: " + (contadorJogadas + 1), 
            "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Restarts the game (Menu option)
    protected static ActionListener restart_game(Window current_window){
        return e -> {
            Main.playClick("songs/click.wav");
            
            // Reset logic state
            turnoAtual = charPecaCabrito;
            pecaSelecionada = null;
            contadorJogadas = 0;
            superPuloUsado = false;
            jogoEmAndamento = true;

            // Reset visual state to initial positions (C at c1, A at c5)
            ((Circle) Elements.circle1).changeColor(Color.BLACK);
            ((Circle) Elements.circle1).setLetter(charPecaCabrito); 

            ((Circle) Elements.circle2).changeColor(Color.BLACK);
            ((Circle) Elements.circle2).setLetter(" "); 

            ((Circle) Elements.circle3).changeColor(Color.BLACK);
            ((Circle) Elements.circle3).setLetter(" "); 

            ((Circle) Elements.circle4).changeColor(Color.BLACK);
            ((Circle) Elements.circle4).setLetter(charPecaCaracara); 

            ((Circle) Elements.circle5).changeColor(Color.BLACK);
            ((Circle) Elements.circle5).setLetter(" "); 

            ((Circle) Elements.circle6).changeColor(Color.BLACK);
            ((Circle) Elements.circle6).setLetter(" "); 
            
            circle2.setSize(81,81);
            line5.setVisible(true);
            line4.setVisible(true);
            line3.setVisible(true);
            
            plays.setText("Rounds: 0");
            player.setText("Current player: Cabrito");

            status.setBackground(new Color(55, 55, 55));


            
            current_window.repaint();
            JOptionPane.showMessageDialog(null, "O jogo foi reiniciado. O Cabrito começa jogando.", "Jogo Reiniciado", JOptionPane.INFORMATION_MESSAGE);
        };
    }

    // Exits the game (Menu option)
    protected static MouseAdapter exit_game(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.playClick("songs/click.wav");
                System.out.println("See ya!");
                System.exit(0); // Terminate the application
            }
        };
    }

    // Shows authors information (Menu option)
    protected static ActionListener show_authors(){
        return e ->{
            Main.playClick("songs/click.wav");
            JOptionPane.showMessageDialog(null, "Graphic interface and half logic made by Sidnei Correia Junior.\nHalf logic made by Adriano Moisés.", "Authors", JOptionPane.INFORMATION_MESSAGE);
        };
    }
}