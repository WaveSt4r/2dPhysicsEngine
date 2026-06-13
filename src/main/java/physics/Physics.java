package physics;

import core.Game;
import math.Segment;
import physics.collider.ColliderType;
import physics.collision.CollisionDetector;
import physics.collision.CollisionMap;
import physics.collision.CollisionSolver;

import java.util.List;

public abstract class Physics {
    public static CollisionMap collisionMap;

    public static void start() {
        collisionMap = new CollisionMap();
    }

    public static void update(List<RigidBody2D> rigidBody2DList) {
        int len = rigidBody2DList.size();

        for (RigidBody2D rb1 : rigidBody2DList) {
            // apply gravity
            if (rb1.mass > 0 && !rb1.isStatic && rb1 != Game.selectedRigidBody) {
                rb1.velocity.y += Game.gravity;
            }

            // apply speed
            rb1.position.add(rb1.velocity);
        }

        //solve collisions
        for (int i = 0; i < len; i++) {
            RigidBody2D rb1 = rigidBody2DList.get(i);
            for (int j = i + 1; j < len; j++) {
                RigidBody2D rb2 = rigidBody2DList.get(j);
                collisionMap.acceptCollision(rb1, rb2);
            }
        }

    }

    public static void manageCircleBoxCollision(RigidBody2D cRB, RigidBody2D bRB) {
        if (cRB.collisionShape.colliderType == ColliderType.BOX) {
            RigidBody2D t = cRB;
            cRB = bRB;
            bRB = t;
        }

        if (CollisionDetector.checkCircleBoxCollision(cRB, bRB)) {
            CollisionSolver.solveCircleBoxCollision(cRB, bRB);
        }
    }

    public static void manageCircleCircleCollision(RigidBody2D circle1, RigidBody2D circle2) {
        //System.out.println("circle1: " + circle1.position + "\tcircle2: " + circle2.position);
        if (CollisionDetector.checkCircleCircleCollision(circle1, circle2)) {
            CollisionSolver.solveCircleCircleCollision(circle1, circle2);
        }
    }

    public static void manageCirclePolygonCollision(RigidBody2D c, RigidBody2D p) {
        if (c.collisionShape.colliderType == ColliderType.POLYGON) {
            RigidBody2D t = c;
            c = p;
            p = t;
        }

        Segment seg = CollisionDetector.checkCirclePolygonCollision(c, p);
        if (seg != null) {
            CollisionSolver.solveCircleSegmentCollision(c, seg);
        }
    }
}
