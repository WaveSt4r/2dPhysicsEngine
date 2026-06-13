package physics.collider;

import math.Vector2;

public class CircleCollider extends CollisionShape {
    public double radius;

    public CircleCollider(double radius) {
        super(ColliderType.CIRCLE);
        this.radius = radius;
    }

    @Override
    public boolean isPointInside(Vector2 point) {
        return (point.magnitudeSquared() <= this.radius * this.radius);
    }

    public String toString() {
        return "physics.collider.CircleCollider\tR: " + radius;
    }
}
