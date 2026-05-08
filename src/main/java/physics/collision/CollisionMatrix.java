package physics.collision;

import physics.Physics;
import physics.RigidBody2D;
import physics.collider.ColliderType;

import java.util.function.BiConsumer;

public class CollisionMatrix {
    public BiConsumer<RigidBody2D, RigidBody2D>[][] collisionMatrix;

    public CollisionMatrix () {
        int size = ColliderType.values().length;
        collisionMatrix = new BiConsumer[size][size];

        collisionMatrix[ColliderType.CIRCLE.ordinal()][ColliderType.BOX.ordinal()] = Physics::manageCircleBoxCollision;
    }

    public void acceptCollision (RigidBody2D rb1, RigidBody2D rb2) {
        ColliderType t1 = rb1.collisionShape.colliderType;
        ColliderType t2 = rb2.collisionShape.colliderType;

        BiConsumer<RigidBody2D, RigidBody2D> func = collisionMatrix[t1.ordinal()][t2.ordinal()];

        if (func != null) {
            func.accept(rb1, rb2);
        }
    }
}
