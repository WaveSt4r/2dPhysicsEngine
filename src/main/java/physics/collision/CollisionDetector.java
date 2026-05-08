package physics.collision;

import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;

public class CollisionDetector {
    public static boolean checkCircleBoxCollision (RigidBody2D cRB, RigidBody2D bRB) {
        CircleCollider aCC = (CircleCollider) cRB.collisionShape;
        BoxCollider bC = (BoxCollider) bRB.collisionShape;
        if (cRB.position.x+aCC.radius > bRB.position.x && cRB.position.x-aCC.radius < bRB.position.x+ bC.width) {
            if (cRB.position.y+aCC.radius > bRB.position.y && cRB.position.y-aCC.radius < bRB.position.y+ bC.height) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCircleCircleCollision (RigidBody2D rb1, RigidBody2D rb2) {
        CircleCollider cc1 = (CircleCollider) rb1.collisionShape;
        CircleCollider cc2 = (CircleCollider) rb2.collisionShape;
        return rb1.position.distanceSquared(rb2.position) <= Math.max(cc1.radius, cc2.radius);
    }
}
