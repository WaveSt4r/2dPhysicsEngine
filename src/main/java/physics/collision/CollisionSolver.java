package physics.collision;

import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;

public class CollisionSolver {
    public static void solveCircleBoxCollision(RigidBody2D cRB, RigidBody2D bRB) {
        CircleCollider aCC = (CircleCollider) cRB.collisionShape;
        BoxCollider bC = (BoxCollider) bRB.collisionShape;
        if (cRB.position.x < bRB.position.x || cRB.position.x > bRB.position.x+ bC.width) {
            cRB.velocity.x *= -1;
        }
        if (cRB.position.y < bRB.position.y || cRB.position.y > bRB.position.y+ bC.height) {
            cRB.velocity.y *= -1;
        }
    }
}
