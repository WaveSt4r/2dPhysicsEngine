package physics.collider;

import math.Vector2;

public class BoxCollider extends CollisionShape {
    public double width;
    public double height;

    public BoxCollider(double width, double height) {
        super(ColliderType.BOX);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isPointInside(Vector2 point) {
        return (point.x >= 0 && point.x < this.width && point.y >= 0 && point.y < this.height);
    }

    public String toString() {
        return "physics.collider.BoxCollider\tW: " + width + "\tH: " + height;
    }
}
