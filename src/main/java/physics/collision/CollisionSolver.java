package physics.collision;

import math.Vector2;
import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;

public class CollisionSolver {
    public static void solveCircleBoxCollision(RigidBody2D c, RigidBody2D b) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        BoxCollider bc = (BoxCollider) b.collisionShape;
        if (c.position.x < b.position.x || c.position.x > b.position.x+bc.width) {
            c.position.x = (c.position.x < b.position.x) ? b.position.x - cc.radius : b.position.x + bc.width + cc.radius;
            c.velocity.x *= -1;
        }
        if (c.position.y < b.position.y || c.position.y > b.position.y+bc.height) {
            c.position.y = (c.position.y < b.position.y) ? b.position.y - cc.radius : b.position.y + bc.height + cc.radius;
            c.velocity.y *= -1;
        }
    }

    public static void solveCircleCircleCollision (RigidBody2D c1, RigidBody2D c2) {
        CircleCollider cc1 = (CircleCollider) c1.collisionShape;
        CircleCollider cc2 = (CircleCollider) c2.collisionShape;

        // vector that goes through both circle's centers, I'll call it Normal Vector or nV
        Vector2 nV = new Vector2 (c2.position.x - c1.position.x, c2.position.y - c1.position.y);
        nV.multiply(1d/(nV.magnitude())); // nV normalized

        // vector that is tangent to the point of collision, I'll call it Tangent Vector or tV, it's nV but rotated 90 degrees
        Vector2 tV = new Vector2(nV.y, nV.x*-1);

        // we project the speed of the first circle into tV and nV to get respectively the new c1 and c2 magnitudes
        double c1nM1 = Vector2.dotProduct(c1.velocity, tV);
        double c2nM1 = Vector2.dotProduct(c1.velocity, nV);

        // we multiply tV and nV with these two magnitudes to find the new velocity vectors
        Vector2 c1nV1 = Vector2.multiply(tV, c1nM1);
        Vector2 c2nV1 = Vector2.multiply(nV, c2nM1);

        // we do the same thing with c2 but inverting nV and tV
        nV.multiply(-1);
        //tV.multiply(-1);

        // we project the speed of the second circle into tV and nV to get respectively the new c2 and c1 magnitudes
        double c2nM2 = Vector2.dotProduct(c2.velocity, tV);
        double c1nM2 = Vector2.dotProduct(c2.velocity, nV);

        // we multiply tV and nV with these two magnitudes to find the new velocity vectors
        Vector2 c2nV2 = Vector2.multiply(tV, c2nM2);
        Vector2 c1nV2 = Vector2.multiply(nV, c1nM2);

        // finally we assign both the circles their new velocity which is the sum of their two velocities calculated
        c1.velocity = Vector2.sum(c1nV1, c1nV2);
        c2.velocity = Vector2.sum(c2nV1, c2nV2);
    }
}
