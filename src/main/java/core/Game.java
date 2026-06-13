package core;

import math.Vector2;
import physics.RigidBody2D;
import render.ColorRGB;
import render.Renderer;
import scenes.Scene2;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    public boolean running = true;
    public final static int DT = 16;
    public static double gravity = 0.3;

    public static Scene currentScene;
    public static RigidBody2D selectedRigidBody;

    public Vector2 mousePos = new Vector2();
    public Vector2 prevMousePos = new Vector2();

    public static ColorRGB backgroundColor = new ColorRGB(15, 80, 115);


    public void start() {
        currentScene = Scene2.load();
    }


    public void loop() {
        int n = 1000;
        int i = 0;
        int waitTime = 5;
        int t = 0;

        long target = 16_666_667; // 60 FPS
        long elapsed;

        while (running) {
            long start = System.nanoTime();

            // Clear Buffer
            Renderer.clearToColor(backgroundColor);

            // Spawn things
//            t++;
//            if(i<n && t==waitTime) {
//                RigidBody2D ball = new RigidBody2D(
//                        new Vector2(500, 100),
//                        1d,
//                        new CircleCollider(20),
//                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(255,255,255)))
//                );
//                ball.restitution = .6;
//                ball.friction = 0.02;
//                ball.velocity.set(Math.random()*5,Math.random()*5);
//                currentScene.add(ball);
//                i++;
//                t=0;
//            }

            // Update
            currentScene.update();

            // Move objects with mouse
            if (Launcher.frame.getMousePosition() != null) {
                prevMousePos.set(mousePos);
                mousePos.set(Launcher.frame.getMousePosition().x, Launcher.frame.getMousePosition().y - 30);
            } else {
                selectedRigidBody = null;
            }
            if (selectedRigidBody != null) {
                selectedRigidBody.velocity.set(mousePos.x - prevMousePos.x, mousePos.y - prevMousePos.y);
            }

            // Draw
            currentScene.draw();

            // PostProcess
            //Renderer.fxaa();

            // Repaint
            Launcher.repaintPanel();


            // 60 FPS
//            elapsed = System.nanoTime() - start;
//
//            if (elapsed < target) {
//                try {
//                    Thread.sleep((target - elapsed) / 1_000_000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            // 60 fps
            try {
                Thread.sleep(DT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void createListeners(Frame frame) {
        // Exit the loop when Esc key is pressed
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    running = false;
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

        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Vector2 mousePos = new Vector2(e.getPoint().x, e.getPoint().y - 30);
                Vector2 relMousePos = new Vector2();
                for (RigidBody2D rb : currentScene.rigidBody2DList) {
                    relMousePos.set(mousePos.x - rb.position.x, mousePos.y - rb.position.y);
                    //System.out.println(relMousePos);
                    if (rb.collisionShape.isPointInside(relMousePos)) {
                        selectedRigidBody = rb;
                        //System.out.println("Caught a wild rigid body: " + rb);
                        break;
                    }
                }
                //System.out.println("Mouse pressed");
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                super.mousePressed(e);
                if (selectedRigidBody != null) {
                    if (selectedRigidBody.isStatic) {
                        selectedRigidBody.velocity.set(0, 0);
                    }
                }
                selectedRigidBody = null;
            }
        });
    }
}
