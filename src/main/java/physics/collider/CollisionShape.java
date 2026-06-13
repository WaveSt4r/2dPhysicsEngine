package physics.collider;

import math.Vector2;

public class CollisionShape {
    public ColliderType colliderType;

    public CollisionShape(ColliderType colliderType) {
        this.colliderType = colliderType;
    }

    public boolean isPointInside(Vector2 point) {
        return false;
    }
}
