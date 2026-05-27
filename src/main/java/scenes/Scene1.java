package scenes;

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
import java.util.List;

public class Scene1 {
    static ArrayList<RigidBody2D> rigidBody2DList = new ArrayList<>();

    public static ColorRGB borderColor = new ColorRGB(255,255,255);
    public static ColorRGB wallsColor = new ColorRGB(97,157,91);
    public static GraphicElements2D wallsGraphicElements2D = new GraphicElements2D(wallsColor, borderColor, 5);

    public static void sceneContent () {
        //Ball
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(100, 300),
                        new Vector2(5,0),
                        1d,
                        new CircleCollider(20),
                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(255,0,0))),
                        false
                )
        );

        //Ball2
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(500, 280),
                        new Vector2(0,0),
                        1d,
                        new CircleCollider(20),
                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(0,255,0))),
                        false
                )
        );

        //Floor
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0,550),
                        new Vector2(0,0),
                        1d,
                        new BoxCollider(800, 50),
                        new BoxMesh(800, 50, wallsGraphicElements2D),
                        true
                )
        );

        // Left Wall
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(750,0),
                        new Vector2(0,0),
                        1d,
                        new BoxCollider(50, 600),
                        new BoxMesh(50, 600, wallsGraphicElements2D),
                        true
                )
        );

        // Right Wall
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0,0),
                        new Vector2(0,0),
                        1d,
                        new BoxCollider(50, 600),
                        new BoxMesh(50, 600, wallsGraphicElements2D),
                        true
                )
        );

        // Ceiling
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0,0),
                        new Vector2(0,0),
                        1d,
                        new BoxCollider(800, 25),
                        new BoxMesh(800, 25, wallsGraphicElements2D),
                        true
                )
        );
    }

    public static Scene load () {
        sceneContent();
        return new Scene(rigidBody2DList);
    }
}
