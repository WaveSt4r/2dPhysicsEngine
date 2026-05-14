package math;

public class Vector2 {
    public double x;
    public double y;

    public Vector2 (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 (Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public static Vector2 sum(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2 multiply(Vector2 v1, double s) {
        return new Vector2(v1.x * s, v1.y * s);
    }

    public void add (Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void multiply (double s) {
        this.x *= s;
        this.y *= s;
    }

    public void set (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceSquared (Vector2 v) {
        return (x - v.x) * (x - v.x) + (y - v.y) * (y - v.y);
    }

    public double magnitudeSquared () {
        return x*x+y*y;
    }

    public double magnitude () {
        return Math.sqrt(magnitudeSquared());
    }

    public void setMagnitude (double magnitude) {
        x = x * (magnitude/magnitude());
        y = y * (magnitude/magnitude());
    }

    public double dotProduct(Vector2 v) {
        return (this.x*v.x)+(this.y*v.y);
    }

    public static double dotProduct(Vector2 v1, Vector2 v2) {
        return (v1.x*v2.x)+(v1.y*v2.y);
    }

    @Override
    public String toString () {
        return "( " + x + " ; " + y + " )";
    }
}
