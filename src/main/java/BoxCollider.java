public class BoxCollider extends CollisionShape {
    double width;
    double height;

    public BoxCollider(double width, double height) {
        super(ColliderType.BOX);
        this.width = width;
        this.height = height;
    }

    public String toString () {
        return "BoxCollider\tW: " + width + "\tH: " + height;
    }
}
