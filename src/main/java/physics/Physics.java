package physics;

import core.Game;
import physics.collider.ColliderType;
import physics.collision.CollisionDetector;
import physics.collision.CollisionMatrix;
import physics.collision.CollisionSolver;

import java.util.List;

public abstract class Physics {
    public static CollisionMatrix collisionMatrix;

    public static void start() {
        collisionMatrix = new CollisionMatrix();
    }

    public static void update (List<RigidBody2D> rigidBody2DList) {
        int len = rigidBody2DList.size();

        for (int i = 0; i < len; i++) {
            RigidBody2D rb1 = rigidBody2DList.get(i);

            // apply gravity
            if (rb1.mass>0 && !rb1.isStatic) {
                rb1.velocity.y += Game.gravity;
            }

            // apply speed
            rb1.position.add(rb1.velocity);
        }

        //solve collisions
        for (int i = 0; i < len; i++) {
            RigidBody2D rb1 = rigidBody2DList.get(i);
            for(int j = i + 1; j < len; j++) {
                RigidBody2D rb2 = rigidBody2DList.get(j);
                collisionMatrix.acceptCollision(rb1, rb2);
            }
        }
    }

    public static void manageCircleBoxCollision(RigidBody2D cRB, RigidBody2D bRB) {
        if (cRB.collisionShape.colliderType == ColliderType.BOX) {
            RigidBody2D t = cRB;
            cRB = bRB;
            bRB = t;
        }

        if(CollisionDetector.checkCircleBoxCollision(cRB, bRB)) {
            CollisionSolver.solveCircleBoxCollision(cRB, bRB);
        }
    }

    public static void manageCircleCircleCollision(RigidBody2D circle1, RigidBody2D circle2) {
        //System.out.println("circle1: " + circle1.position + "\tcircle2: " + circle2.position);
        if(CollisionDetector.checkCircleCircleCollision(circle1, circle2)) {
            CollisionSolver.solveCircleCircleCollision(circle1, circle2);
        }
    }
}
