public class CircleCollider extends CollisionShape {
    public double radius;

    public CircleCollider(double radius) {
        super(ColliderType.CIRCLE);
        this.radius = radius;
    }

    public String toString () {
        return "CircleCollider\tR: " + radius;
    }
}
