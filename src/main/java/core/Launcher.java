package core;

import physics.Physics;
import render.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Launcher extends JPanel {
    public static BufferedImage img;
    public static JFrame frame;

    public static int width, height;

    // Draw in the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        // Create the window
        frame = new JFrame("Physics Engine 2D");

        // Create the panel
        Launcher panel = new Launcher();

        // Add the panel to the window
        frame.add(panel);

        // Modify window size
        frame.setUndecorated(false);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000, 800);
        frame.setVisible(true);

        width = frame.getWidth();
        height = frame.getHeight();

        // Close the window with x
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a buffered image
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Attach the buffer to the render
        Renderer.initialize(img);


        // Create a Game object
        Game game = new Game();
        // Create Key listeners
        game.createListeners(frame);
        // Start the physics engin
        Physics.start();

        // GAME START
        game.start();

        // GAME LOOP
        game.loop();
    }

    public static void repaintPanel() {
        frame.repaint();
    }
}
