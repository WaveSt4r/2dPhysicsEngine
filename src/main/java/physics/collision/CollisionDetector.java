package physics.collision;

import physics.RigidBody2D;
import physics.collider.BoxCollider;
import physics.collider.CircleCollider;

public class CollisionDetector {
    public static boolean checkCircleBoxCollision (RigidBody2D c, RigidBody2D b) {
        CircleCollider cc = (CircleCollider) c.collisionShape;
        BoxCollider bc = (BoxCollider) b.collisionShape;
        if (c.position.x+cc.radius >= b.position.x && c.position.x-cc.radius <= b.position.x+bc.width) {
            if (c.position.y+cc.radius >= b.position.y && c.position.y-cc.radius <= b.position.y+bc.height) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCircleCircleCollision (RigidBody2D circle1, RigidBody2D circle2) {
        CircleCollider cc1 = (CircleCollider) circle1.collisionShape;
        CircleCollider cc2 = (CircleCollider) circle2.collisionShape;
        return circle1.position.distanceSquared(circle2.position) <= Math.max(4*cc1.radius*cc1.radius, 4*cc2.radius*cc2.radius);
    }
}
