import java.util.List;
import java.util.function.BiConsumer;

public abstract class Physics {
    public static BiConsumer<RigidBody2D, RigidBody2D>[][] collisionMatrix;

    public static void start() {
        int size = ColliderType.values().length;
        collisionMatrix = new BiConsumer[size][size];

        collisionMatrix[ColliderType.CIRCLE.ordinal()][ColliderType.BOX.ordinal()] = Physics::solveCircleBoxCollision;
    }

    public static void update (List<RigidBody2D> rigidBody2DList) {
        for (int i = 0; i < rigidBody2DList.size(); i++) {
            RigidBody2D rb1 = rigidBody2DList.get(i);

            // apply speed
            rb1.position.add(rb1.velocity);

            // solve collisions
            for(int j = i + 1; j <  rigidBody2DList.size(); j++) {
                RigidBody2D rb2 = rigidBody2DList.get(j);
                solveIndividualCollision(rb1, rb2);
            }
        }
    }

    static void solveIndividualCollision (RigidBody2D rb1, RigidBody2D rb2) {
        ColliderType t1 = rb1.collisionShape.colliderType;
        ColliderType t2 = rb2.collisionShape.colliderType;

        BiConsumer<RigidBody2D, RigidBody2D> func = collisionMatrix[t1.ordinal()][t2.ordinal()];

        if (func != null) {
            func.accept(rb1, rb2);
        }
    }

    static boolean checkCircleBoxCollision (RigidBody2D cRB, RigidBody2D bRB) {
        CircleCollider aCC = (CircleCollider) cRB.collisionShape;
        BoxCollider bC = (BoxCollider) bRB.collisionShape;
        if (cRB.position.x+aCC.radius > bRB.position.x && cRB.position.x-aCC.radius < bRB.position.x+ bC.width) {
            if (cRB.position.y+aCC.radius > bRB.position.y && cRB.position.y-aCC.radius < bRB.position.y+ bC.height) {
                return true;
            }
        }
        return false;
    }

    static void solveCircleBoxCollision (RigidBody2D cRB, RigidBody2D bRB) {
        if (!checkCircleBoxCollision(cRB, bRB)) {
            return;
        }

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
