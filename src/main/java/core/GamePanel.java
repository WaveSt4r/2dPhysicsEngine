package core;

import physics.Physics;
import render.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    static final int width = 800;
    static final int height = 600;
    static BufferedImage img;
    static JFrame frame;

    // Disegna nel panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        //System.out.println("print");
    }

    public static void main (String[] args) {
        // Crea finestra / Shell in SWT
        frame = new JFrame();

        // Crea il Pannello / Come quando si crea un canvas
        GamePanel panel = new GamePanel();

        // Aggiunge il Pannello nella finestra / Aggiunge il canvas alla finestra
        frame.add(panel);

        // Modifica la dimensione della finestra
        frame.setSize(width,height);
        // Rende la finestra visibile
        frame.setVisible(true);

        // Chiude la finestra con la X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea un buffer immagine
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Attacca il buffer al renderer
        Renderer.initialize(img);


        // Crea oggetto core.Game
        Game game = new Game();
        // Crea Key listeners
        game.createKeyListeners(frame);
        // Avvia il motore fisico
        Physics.start();

        // GAME START
        game.start();

        // GAME LOOP
        game.loop();

        // ciao ciao
        System.out.println("ciao ciao");
    }

    public static void repaintPanel() {
        frame.repaint();
    }
}
