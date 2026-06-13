package scenes;

import core.Launcher;
import core.Scene;
import math.Vector2;
import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;
import render.ColorRGB;
import render.GraphicElements2D;
import render.mesh.BoxMesh;
import render.mesh.CircleMesh;

import java.util.ArrayList;

public class Scene1 {
    static ArrayList<RigidBody2D> rigidBody2DList = new ArrayList<>();

    public static ColorRGB borderColor = new ColorRGB(255, 255, 255);
    public static ColorRGB wallsColor = new ColorRGB(97, 157, 91);
    public static GraphicElements2D wallsGraphicElements2D = new GraphicElements2D(wallsColor, borderColor, 5);

    public static void sceneContent() {
        //Ball
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(300, 300),
                        new Vector2(10, 0),
                        1d,
                        new CircleCollider(20),
                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(255, 0, 0), new ColorRGB(255, 255, 255), 0)),
                        false
                )
        );
        rigidBody2DList.getLast().restitution = 0.7;
        rigidBody2DList.getLast().friction = .01;

        //Ball2
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(500, 300),
                        new Vector2(0, 0),
                        1d,
                        new CircleCollider(20),
                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(0, 255, 0), new ColorRGB(255, 255, 255), 0)),
                        false
                )
        );
        rigidBody2DList.getLast().restitution = 0.7;
        rigidBody2DList.getLast().friction = .01;

        //Floor
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0, Launcher.height - 50),
                        new Vector2(0, 0),
                        1d,
                        new BoxCollider(Launcher.width, 500),
                        new BoxMesh(Launcher.width, 500, wallsGraphicElements2D),
                        true
                )
        );

        // Right Wall
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(Launcher.width - 50, 0),
                        new Vector2(0, 0),
                        1d,
                        new BoxCollider(500, Launcher.height),
                        new BoxMesh(500, Launcher.height, wallsGraphicElements2D),
                        true
                )
        );

        // Left Wall
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(-450, 0),
                        new Vector2(0, 0),
                        1d,
                        new BoxCollider(500, Launcher.height),
                        new BoxMesh(500, Launcher.height, wallsGraphicElements2D),
                        true
                )
        );

        // Ceiling
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0, -475),
                        new Vector2(0, 0),
                        1d,
                        new BoxCollider(Launcher.width, 500),
                        new BoxMesh(Launcher.width, 500, wallsGraphicElements2D),
                        true
                )
        );
    }

    public static Scene load() {
        sceneContent();
        return new Scene(rigidBody2DList);
    }
}
