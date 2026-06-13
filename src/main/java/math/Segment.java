package math;

public class Segment {
    public Vector2 startPos;
    public Vector2 endPos;

    public Segment(Vector2 startPos, Vector2 endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public Segment(Segment segment) {
        this.startPos = new Vector2(segment.startPos);
        this.endPos = new Vector2(segment.endPos);
    }

    public void transform(Vector2 t) {
        this.startPos.add(t);
        this.endPos.add(t);
    }

    public double getLength() {
        return getDelta().magnitude();
    }

    public double getLengthSquared() {
        return getDelta().magnitudeSquared();
    }

    public Vector2 getDelta() {
        return Vector2.subtraction(endPos, startPos);
    }

    public Vector2 getNormal() {
        return Vector2.normalize(Vector2.rotate90(getDelta()));
    }

    public Vector2 getTangent() {
        return Vector2.normalize(getDelta());
    }
}
