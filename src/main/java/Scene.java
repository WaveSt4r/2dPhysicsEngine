import java.util.List;

public class Scene {
    List<RigidBody2D> rigidBody2DList;

    public Scene (List<RigidBody2D> rigidBody2DList) {
        this.rigidBody2DList = rigidBody2DList;
    }

    public void add (RigidBody2D rigidBody2D) {
        rigidBody2DList.add(rigidBody2D);
    }

    public void update () {
        for(RigidBody2D rb : rigidBody2DList) {
            rb.update();
        }
        Physics.update(rigidBody2DList);
    }

    public void draw () {
        for(RigidBody2D rb : rigidBody2DList) {
            rb.draw();
        }
    }
}
