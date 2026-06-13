package scenes;

import core.Scene;
import math.Vector2;
import physics.RigidBody2D;
import physics.collider.CircleCollider;
import physics.collider.PolygonCollider;
import render.ColorRGB;
import render.GraphicElements2D;
import render.mesh.CircleMesh;
import render.mesh.PolygonMesh;

import java.util.ArrayList;

public class Scene2 {
    static ArrayList<RigidBody2D> rigidBody2DList = new ArrayList<>();

    public static ColorRGB borderColor = new ColorRGB(255, 255, 255);
    public static ColorRGB wallsColor = new ColorRGB(97, 157, 91);
    public static GraphicElements2D wallsGraphicElements2D = new GraphicElements2D(wallsColor, borderColor, 5);

    public static void sceneContent() {
        // BALL
        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(500, 100),
                        new Vector2(0, 0),
                        1d,
                        new CircleCollider(20),
                        new CircleMesh(20, new GraphicElements2D(new ColorRGB(255, 255, 255))),
                        false
                )
        );
        rigidBody2DList.getLast().restitution = 0.7;
        rigidBody2DList.getLast().friction = .01;

        Vector2[] points = {
                new Vector2(0, 0),
                new Vector2(160, 250),
                new Vector2(320, 350),
                new Vector2(475, 400),
                new Vector2(475, 800),
                new Vector2(0, 800)
        };

        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(0, 0),
                        0d,
                        new PolygonCollider(points),
                        new PolygonMesh(points, wallsGraphicElements2D)
                )
        );

        Vector2[] points2 = {
                new Vector2(0, 0),
                new Vector2(0, 800),
                new Vector2(-475, 800),
                new Vector2(-475, 400),
                new Vector2(-320, 350),
                new Vector2(-160, 250)
        };

        rigidBody2DList.add(
                new RigidBody2D(
                        new Vector2(1000, 0),
                        0d,
                        new PolygonCollider(points2),
                        new PolygonMesh(points2, wallsGraphicElements2D)
                )
        );
    }

    public static Scene load() {
        sceneContent();
        return new Scene(rigidBody2DList);
    }
}
