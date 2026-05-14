package core;

import math.Vector2;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;
import physics.RigidBody2D;
import render.*;
import render.mesh.BoxMesh;
import render.mesh.CircleMesh;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public boolean game = true;
    public Scene currentScene;

    public static ColorRGB backgroundColor = new ColorRGB(15,80,115);
    public static ColorRGB foregroundColor = new ColorRGB(255,255,255);
    public static ColorRGB borderColor = new ColorRGB(255,255,255);
    public static ColorRGB wallsColor = new ColorRGB(97,157,91);

    public static double gravity = 0;

    public void start () {
        List<RigidBody2D> rigidBody2DList = new ArrayList<>();
        Scene scene1 = new Scene(rigidBody2DList);

        // Ball
        RigidBody2D ball = new RigidBody2D(
                new Vector2(100, 300),
                1d,
                new CircleCollider(20),
                new CircleMesh(20, new GraphicElements2D(new ColorRGB(255,0,0)))
        );
        ball.velocity.set(5,0);
        scene1.add(ball);

        // Ball2
        RigidBody2D ball2 = new RigidBody2D(
                new Vector2(500, 280),
                1d,
                new CircleCollider(20),
                new CircleMesh(20, new GraphicElements2D(new ColorRGB(0,255,0)))
        );
        ball2.velocity.set(0,0);
        scene1.add(ball2);


        // Box
        GraphicElements2D wallsGraphicElements2D = new GraphicElements2D(wallsColor, borderColor, 5);
//        RigidBody2D box = new RigidBody2D(
//                new Vector2(500,400),
//                1d,
//                new BoxCollider(50, 50),
//                new BoxMesh(50, 50, wallsGraphicElements2D)
//        );
//        scene1.add(box);


        // Floor
        RigidBody2D floor = new RigidBody2D(
                new Vector2(0,550),
                1d,
                new BoxCollider(800, 50),
                new BoxMesh(800, 50, wallsGraphicElements2D)
        );
        floor.isStatic = true;
        scene1.add(floor);


        // Right Wall
        RigidBody2D leftWall = new RigidBody2D(
                new Vector2(750,0),
                1d,
                new BoxCollider(50, 600),
                new BoxMesh(50, 600, wallsGraphicElements2D)
        );
        leftWall.isStatic = true;
        scene1.add(leftWall);


        // Right Wall
        RigidBody2D rightWall = new RigidBody2D(
                new Vector2(0,0),
                1d,
                new BoxCollider(50, 600),
                new BoxMesh(50, 600, wallsGraphicElements2D)
        );
        rightWall.isStatic = true;
        scene1.add(rightWall);


        // ceiling Wall
        RigidBody2D ceiling = new RigidBody2D(
                new Vector2(0,0),
                1d,
                new BoxCollider(800, 25),
                new BoxMesh(800, 25, wallsGraphicElements2D)
        );
        ceiling.isStatic = true;
        scene1.add(ceiling);


        currentScene = scene1;
    }


    public void loop () {
        while (game) {
            // CLear Buffer
            Renderer.clearToColor(backgroundColor);


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
                Thread.sleep(16);
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
