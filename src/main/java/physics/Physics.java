package physics;

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
        for (int i = 0; i < rigidBody2DList.size(); i++) {
            RigidBody2D rb1 = rigidBody2DList.get(i);

            // apply speed
            rb1.position.add(rb1.velocity);

            // solve collisions
            for(int j = i + 1; j <  rigidBody2DList.size(); j++) {
                RigidBody2D rb2 = rigidBody2DList.get(j);
                collisionMatrix.acceptCollision(rb1, rb2);
            }
        }
    }

    public static void manageCircleBoxCollision(RigidBody2D cRB, RigidBody2D bRB) {
        if(CollisionDetector.checkCircleBoxCollision(cRB, bRB)) {
            CollisionSolver.solveCircleBoxCollision(cRB, bRB);
        }
    }
}
