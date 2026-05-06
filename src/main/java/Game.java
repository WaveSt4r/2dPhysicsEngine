import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public boolean game = true;
    public Scene currentScene;

    static Color backgroundColor = new Color(0, 202, 224);
    static Color foregroundColor = new Color(255,255,255);
    static Color borderColor = new Color(0,0,0);

    public void start () {
        List<RigidBody2D> rigidBody2DList = new ArrayList<>();
        Scene scene1 = new Scene(rigidBody2DList);

        // Ball
        GraphicElements2D ballGraphicElements2D = new GraphicElements2D(foregroundColor, borderColor, 3);
        RigidBody2D ball = new RigidBody2D(
                new Vector2(200, 100),
                1d,
                new CircleCollider(20),
                new CircleMesh(20, ballGraphicElements2D)
        );
        ball.velocity = new Vector2(1,1);
        scene1.add(ball);


        // Box
        RigidBody2D box = new RigidBody2D(
                new Vector2(500,400),
                1d,
                new BoxCollider(50, 50),
                new BoxMesh(50, 50, ballGraphicElements2D)
        );
        scene1.add(box);


        // Floor
        GraphicElements2D rectGraphicElements2D = new GraphicElements2D(foregroundColor, borderColor, 5);
        RigidBody2D floor = new RigidBody2D(
                new Vector2(0,550),
                1d,
                new BoxCollider(800, 50),
                new BoxMesh(800, 50, rectGraphicElements2D)
        );
        scene1.add(floor);


        // Right Wall
        RigidBody2D leftWall = new RigidBody2D(
                new Vector2(750,0),
                1d,
                new BoxCollider(50, 600),
                new BoxMesh(50, 600, rectGraphicElements2D)
        );
        scene1.add(leftWall);


        // Right Wall
        RigidBody2D rightWall = new RigidBody2D(
                new Vector2(0,0),
                1d,
                new BoxCollider(50, 600),
                new BoxMesh(50, 600, rectGraphicElements2D)
        );
        scene1.add(rightWall);


        // ceiling Wall
        RigidBody2D ceiling = new RigidBody2D(
                new Vector2(0,0),
                1d,
                new BoxCollider(800, 25),
                new BoxMesh(800, 25, rectGraphicElements2D)
        );
        scene1.add(ceiling);


        currentScene = scene1;
    }


    public void loop () {
        while (game) {
            // CLear Buffer
            Renderer.clearToColor(backgroundColor);


            // Update
            currentScene.update();


            // Draw
            currentScene.draw();


            // Repaint
            GamePanel.repaintPanel();


            // 60 fps
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void createKeyListeners (Frame frame) {
        // Esci dal game loop quando si preme esc
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    game = false;
                }
            }
        });
    }
}
