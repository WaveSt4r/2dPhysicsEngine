package physics.collision;

import physics.Physics;
import physics.RigidBody2D;
import physics.collider.ColliderType;

import java.util.EnumMap;
import java.util.function.BiConsumer;

public class CollisionMap {
    private final EnumMap<ColliderType, EnumMap<ColliderType, BiConsumer<RigidBody2D, RigidBody2D>>> collisionMap;

    public CollisionMap() {
        collisionMap = new EnumMap<>(ColliderType.class);
        linkMethods();
    }

    private void set(ColliderType a, ColliderType b, BiConsumer<RigidBody2D, RigidBody2D> func) {
        collisionMap
                .computeIfAbsent(a, k -> new EnumMap<>(ColliderType.class))
                .put(b, func);
    }

    public void linkMethods() {
        set(ColliderType.CIRCLE, ColliderType.BOX, Physics::manageCircleBoxCollision);
        set(ColliderType.BOX, ColliderType.CIRCLE, Physics::manageCircleBoxCollision);

        set(ColliderType.CIRCLE, ColliderType.CIRCLE, Physics::manageCircleCircleCollision);

        set(ColliderType.CIRCLE, ColliderType.POLYGON, Physics::manageCirclePolygonCollision);
        set(ColliderType.POLYGON, ColliderType.CIRCLE, Physics::manageCirclePolygonCollision);
    }

    public void acceptCollision(RigidBody2D rb1, RigidBody2D rb2) {
        ColliderType t1 = rb1.collisionShape.colliderType;
        ColliderType t2 = rb2.collisionShape.colliderType;

        EnumMap<ColliderType, BiConsumer<RigidBody2D, RigidBody2D>> row = collisionMap.get(t1);

        if (row == null) return;

        BiConsumer<RigidBody2D, RigidBody2D> func = row.get(t2);

        if (func != null) {
            func.accept(rb1, rb2);
        }
    }
}
