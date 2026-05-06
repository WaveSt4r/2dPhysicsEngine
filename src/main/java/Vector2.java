public class Vector2 {
    public double x;
    public double y;

    public Vector2 (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 sumVector2 (Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public void add (Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void multiply (int i) {
        this.x *= i;
        this.y *= i;
    }

    public void set (Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double distanceSquared (Vector2 v) {
        return (x - v.x) * (x - v.x) + (y - v.y) * (y - v.y);
    }
}
