package core;

import math.Vector2;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;
import physics.RigidBody2D;
import render.*;
import render.mesh.BoxMesh;
import render.mesh.CircleMesh;
import scenes.Scene1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public boolean game = true;
    public final int DT = 16;

    public Scene currentScene;

    public static ColorRGB backgroundColor = new ColorRGB(15,80,115);

    public static double gravity = 0;

    public void start () {
        Scene scene2 = Scene1.load();
        currentScene = scene2;
    }


    public void loop () {
//        int n = 15;
//        int i = 0;
//        int waitTime = 10;
//        int t = 0;
        while (game) {
            // CLear Buffer
            Renderer.clearToColor(backgroundColor);

//            t++;
//            if(i<n && t==waitTime) {
//                RigidBody2D ball = new RigidBody2D(
//                        new Vector2(100, 300),
//                        1d,
//                        new CircleCollider(20),
//                        new CircleMesh(20, new GraphicElements2D(new ColorRGB((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255))))
//                );
//                ball.velocity.set(5,0);
//                currentScene.add(ball);
//                i++;
//                t=0;
//            }

            // Update
            currentScene.update();

            // Draw
            currentScene.draw();

            // PostProcess
            //Renderer.fxaa();

            // Repaint
            GamePanel.repaintPanel();


            // 60 fps
            try {
                Thread.sleep(DT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void createKeyListeners (Frame frame) {
        // Esci dal game loop quando si preme esc
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    game = false;
                }
            }
        });

        // get the color in the middle of the screen
//        frame.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
//                if(e.getKeyCode() == KeyEvent.VK_C) {
//                    ColorRGB c = ColorRGB.extractColor(Renderer.img.getRGB(Renderer.imgWidth/2, Renderer.imgHeight/2));
//                    ColorRGB WHITE = new ColorRGB(255,255,255);
//                    System.out.println(c);
//                    System.out.println("80% white: " + ColorRGB.interpolateColor(c, WHITE, -.2));
//                }
//            }
//        });
    }
}
