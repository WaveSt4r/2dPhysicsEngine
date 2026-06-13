package physics;

import math.Vector2;
import physics.collider.CollisionShape;
import render.mesh.Mesh;

public class RigidBody2D {
    public Vector2 position;
    public Vector2 velocity;
    public double mass;
    public double restitution;
    public double friction;
    public CollisionShape collisionShape;
    public Mesh mesh;

    public boolean isStatic;

    public RigidBody2D(Vector2 position, Double mass, CollisionShape collisionShape, Mesh mesh) {
        this.position = position;
        this.mass = mass;
        this.restitution = 1.0;
        this.friction = 0.0;

        this.collisionShape = collisionShape;

        this.mesh = mesh;

        this.velocity = new Vector2(0, 0);
        this.isStatic = false;
    }

    public RigidBody2D(Vector2 position, Vector2 velocity, double mass, CollisionShape collisionShape, Mesh mesh, boolean isStatic) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.collisionShape = collisionShape;
        this.mesh = mesh;
        this.isStatic = isStatic;

        this.restitution = 1.0;
        this.friction = 0.0;
    }

    public void draw() {
        mesh.draw(position);
    }

    public void update() {
        this.position.add(this.velocity);
    }

    @Override
    public String toString() {
        return "X: " + position.x + "\tY: " + position.y + "\tShape: " + collisionShape + "\trender.mesh.Mesh: " + mesh;
    }

}
