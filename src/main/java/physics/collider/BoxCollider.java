package physics.collider;

public class BoxCollider extends CollisionShape {
    public double width;
    public double height;

    public BoxCollider(double width, double height) {
        super(ColliderType.BOX);
        this.width = width;
        this.height = height;
    }

    public String toString () {
        return "physics.collider.BoxCollider\tW: " + width + "\tH: " + height;
    }
}
