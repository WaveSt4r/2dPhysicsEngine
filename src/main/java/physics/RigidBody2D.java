package physics;

import math.Vector2;
import physics.collider.CollisionShape;
import render.mesh.Mesh;

public class RigidBody2D {
    public Vector2 position;
    public Vector2 velocity;
    public double mass;
    public CollisionShape collisionShape;
    public Mesh mesh;

    public boolean isStatic;

    public RigidBody2D(Vector2 position, Double mass, CollisionShape collisionShape, Mesh mesh) {
        this.position = position;
        this.mass = mass;

        this.collisionShape = collisionShape;

        this.mesh = mesh;

        this.velocity = new Vector2(0,0);
        this.isStatic = false;
    }

    public void draw () {
        mesh.draw(position);
    }

    public void update () {
        this.position.add(this.velocity);
    }

    @Override
    public String toString () {
        return "X: " + position.x + "\tY: " + position.y + "\tShape: " + collisionShape + "\trender.mesh.Mesh: " + mesh;
    }

}
