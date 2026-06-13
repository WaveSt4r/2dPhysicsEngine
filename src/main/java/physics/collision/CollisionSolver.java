package physics.collision;

import math.Segment;
import math.Vector2;
import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;

public class CollisionSolver {
    public static void solveCircleBoxCollision(RigidBody2D c, RigidBody2D b) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        BoxCollider bc = (BoxCollider) b.collisionShape;
        Vector2 startPos = new Vector2();
        Vector2 endPos = new Vector2();

        // LEFT
        if (c.position.x < b.position.x) {
            startPos.set(b.position.x, b.position.y + bc.height);
            endPos.set(b.position.x, b.position.y);
        }

        // RIGHT
        if (c.position.x > b.position.x + bc.width) {
            startPos.set(b.position.x + bc.width, b.position.y);
            endPos.set(b.position.x + bc.width, b.position.y + bc.height);
        }

        // TOP
        if (c.position.y < b.position.y) {
            startPos.set(b.position.x, b.position.y);
            endPos.set(b.position.x + bc.width, b.position.y);
        }

        // BOTTOM
        if (c.position.y > b.position.y + bc.height) {
            startPos.set(b.position.x + bc.width, b.position.y + bc.height);
            endPos.set(b.position.x, b.position.y + bc.height);
        }

        Segment s = new Segment(startPos, endPos);
        solveCircleSegmentCollision(c, s);
    }

    public static void solveCircleCircleCollision(RigidBody2D c1, RigidBody2D c2) {
        // vector that goes through both circle's centers, I'll call it Normal Vector or n
        Vector2 n = new Vector2(c2.position.x - c1.position.x, c2.position.y - c1.position.y);
        double nVMagnitude = n.magnitude();
        n.normalize(); // nV normalized

        // vector that is tangent to the point of collision, I'll call it Tangent Vector or tV, it's nV but rotated 90 degrees
        Vector2 t = Vector2.rotate90(n);

        // fix the position
        CircleCollider cc1 = (CircleCollider) c1.collisionShape;
        CircleCollider cc2 = (CircleCollider) c2.collisionShape;
        double intersection = cc1.radius + cc2.radius - nVMagnitude;
        Vector2 shift = new Vector2(n);
        shift.multiply(intersection / 2);
        c1.position.subtract(shift);
        c2.position.add(shift);

        double relativeSpeedAlongNormal = Vector2.subtraction(c1.velocity, c2.velocity).dotProduct(n);
        double relativeSpeedAlongTangent = Vector2.subtraction(c1.velocity, c2.velocity).dotProduct(t);
        double r = Math.min(c1.restitution, c2.restitution);
        double f = (c1.friction + c2.friction) / 2;

        if (relativeSpeedAlongNormal > 0) {
            double vn = (1 + r) * relativeSpeedAlongNormal / 2;
            double vt = (1 - f) * relativeSpeedAlongTangent;

            Vector2 forceN = Vector2.multiply(n, vn);
            Vector2 forceT = Vector2.multiply(t, vt);

            Vector2 impulse = Vector2.sum(forceN, forceT);

            c1.velocity.subtract(Vector2.division(impulse, c1.mass));
            c2.velocity.add(Vector2.division(impulse, c2.mass));
        }

    }

    public static void solveCircleSegmentCollision(RigidBody2D c, Segment seg) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        Vector2 relCPos = Vector2.subtraction(c.position, seg.startPos);
        double segLength = seg.getLength();

        Vector2 n = new Vector2();
        Vector2 t = new Vector2();

        Vector2 velocityDir = Vector2.normalize(c.velocity);

        double dotTangent = Vector2.dotProduct(seg.getTangent(), relCPos);
        if (dotTangent >= 0 && dotTangent <= segLength) {
            n.set(seg.getNormal());
            t.set(seg.getTangent());

            // Velocity decomposition
            double vn = Vector2.dotProduct(c.velocity, n);
            double vt = Vector2.dotProduct(c.velocity, t);

            double distanceToSegment = Math.abs(
                    Vector2.dotProduct(
                            n,
                            relCPos
                    )
            );

            // Penetration depth
            double penetration = cc.radius - distanceToSegment;

            // Move circle back to contact point
            double correctionDistance = penetration / Math.abs(Vector2.dotProduct(velocityDir, n));

            Vector2 shift = Vector2.multiply(velocityDir, correctionDistance);
            c.position.subtract(shift);

            // Reflect the correction across the segment
            double shiftN = -Vector2.dotProduct(shift, n) * c.restitution;
            double shiftT = Vector2.dotProduct(shift, t) * (1 - c.friction);

            c.position.add(
                    Vector2.sum(
                            Vector2.multiply(n, shiftN),
                            Vector2.multiply(t, shiftT)
                    )
            );

            // Reflect Velocity
            if (vn < 0) {
                vn = vn * -1 * c.restitution;
                if (vn < .5) vn = 0;
                vt = vt * (1 - c.friction);

                c.velocity = Vector2.sum(
                        Vector2.multiply(n, vn),
                        Vector2.multiply(t, vt)
                );
            }
        } else {
            if (dotTangent > segLength) {
                relCPos.set(Vector2.subtraction(c.position, seg.endPos));
            }

            n.set(Vector2.normalize(relCPos));
            t.set(Vector2.rotate90(n));

//            double vn = Vector2.dotProduct(c.velocity, n) * c.restitution;

//            c.velocity.subtract(
//                Vector2.multiply(n, 2.0 * vn)
//            );

            // Velocity decomposition
            double vn = Vector2.dotProduct(c.velocity, n);
            double vt = Vector2.dotProduct(c.velocity, t);

            // Penetration depth
            double penetration = cc.radius - relCPos.magnitude();

            if (penetration > 0) {
                c.position.add(
                        Vector2.multiply(n, penetration)
                );
            }

            if (vn < 0) {
                vn = -vn * c.restitution;
                if (vn < .5) vn = 0;
                vt *= (1 - c.friction);

                c.velocity = Vector2.sum(
                        Vector2.multiply(n, vn),
                        Vector2.multiply(t, vt)
                );
            }
        }
    }
}
