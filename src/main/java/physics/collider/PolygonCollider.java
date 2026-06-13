package physics.collider;

import math.Segment;
import math.Vector2;

public class PolygonCollider extends CollisionShape {
    public Segment[] segments;

    public PolygonCollider(Vector2[] points) {
        super(ColliderType.POLYGON);
        int len = points.length;
        this.segments = new Segment[len];
        for (int i = 0; i < len; i++) {
            this.segments[i] = new Segment(points[i % len], points[(i + 1) % len]);
        }
    }
}
