package math;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public static Vector2 sum(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }


    public static Vector2 subtraction(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }

    public void subtract(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }


    public static Vector2 multiply(Vector2 v1, double s) {
        return new Vector2(v1.x * s, v1.y * s);
    }

    public void multiply(double s) {
        this.x *= s;
        this.y *= s;
    }


    public static Vector2 division(Vector2 v1, double s) {
        return new Vector2(v1.x / s, v1.y / s);
    }

    public void divide(double s) {
        this.x /= s;
        this.y /= s;
    }


    public static Vector2 normalize(Vector2 v) {
        return division(v, v.magnitude());
    }

    public void normalize() {
        this.divide(this.magnitude());
    }


    public static Vector2 rotate90(Vector2 v) {
        return new Vector2(v.y * 1, v.x * -1);
    }


    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double distanceSquared(Vector2 v) {
        return (x - v.x) * (x - v.x) + (y - v.y) * (y - v.y);
    }

    public static double distance(Vector2 v1, Vector2 v2) {
        return Math.sqrt(Math.pow(v2.x - v1.x, 2) + Math.pow(v2.y - v1.y, 2));
    }

    public double magnitudeSquared() {
        return x * x + y * y;
    }

    public double magnitude() {
        return Math.sqrt(magnitudeSquared());
    }

    public void setMagnitude(double magnitude) {
        if (magnitude < 0) {
            x = 0;
            y = 0;
            return;
        }
        if (x != 0 && y != 0) {
            x = x * (magnitude / magnitude());
            y = y * (magnitude / magnitude());
        }
    }

    public double dotProduct(Vector2 v) {
        return (this.x * v.x) + (this.y * v.y);
    }

    public static double dotProduct(Vector2 v1, Vector2 v2) {
        return (v1.x * v2.x) + (v1.y * v2.y);
    }

    public void invert() {
        this.x *= -1;
        this.y *= -1;
    }


    @Override
    public String toString() {
        return "( " + x + " ; " + y + " )";
    }
}
