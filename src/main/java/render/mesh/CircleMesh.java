package render.mesh;

import math.Vector2;
import render.GraphicElements2D;
import render.Renderer;

public class CircleMesh implements Mesh {
    public double radius;
    GraphicElements2D graphicElements2D;

    public CircleMesh(int radius, GraphicElements2D graphicElements2D) {
        this.radius = radius;
        this.graphicElements2D = graphicElements2D;
    }

    public void draw (Vector2 position) {
        Renderer.drawCircle((int) Math.round(position.x),(int) Math.round(position.y), (int) Math.round(radius), graphicElements2D);
    }
}
