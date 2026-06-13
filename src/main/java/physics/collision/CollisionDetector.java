package physics.collision;

import math.Segment;
import math.Vector2;
import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;
import physics.collider.PolygonCollider;

public class CollisionDetector {
    public static boolean checkCircleBoxCollision(RigidBody2D c, RigidBody2D b) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        BoxCollider bc = (BoxCollider) b.collisionShape;
        if (c.position.x + cc.radius >= b.position.x && c.position.x - cc.radius <= b.position.x + bc.width) {
            return c.position.y + cc.radius >= b.position.y && c.position.y - cc.radius <= b.position.y + bc.height;
        }
        return false;
    }

    public static boolean checkCircleCircleCollision(RigidBody2D c1, RigidBody2D c2) {
        CircleCollider cc1 = (CircleCollider) c1.collisionShape;
        CircleCollider cc2 = (CircleCollider) c2.collisionShape;
        return c1.position.distanceSquared(c2.position) <= (cc1.radius + cc2.radius) * (cc1.radius + cc2.radius);
    }

    public static Segment checkCirclePolygonCollision(RigidBody2D c, RigidBody2D p) {
        PolygonCollider pc = (PolygonCollider) p.collisionShape;
        for (Segment s : pc.segments) {
            Segment seg = new Segment(Vector2.sum(s.startPos, p.position), Vector2.sum(s.endPos, p.position));
            if (checkCircleSegmentCollision(c, seg)) {
                return seg;
            }
        }
        return null;
    }

    public static boolean checkCircleSegmentCollision(RigidBody2D c, Segment s) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        Vector2 circleRelativePositionToSegment = Vector2.subtraction(c.position, s.startPos);

        double dotTangent = Vector2.dotProduct(s.getTangent(), circleRelativePositionToSegment);
        if (dotTangent < -cc.radius || dotTangent > s.getLength() + cc.radius) return false;

        double distanceToSegment = Math.abs(Vector2.dotProduct(s.getNormal(), circleRelativePositionToSegment));
        return (distanceToSegment <= cc.radius);
    }
}
