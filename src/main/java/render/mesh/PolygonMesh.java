package render.mesh;

import math.Segment;
import math.Vector2;
import render.GraphicElements2D;
import render.Renderer;

public class PolygonMesh implements Mesh {
    public Vector2[] vertices;
    public Segment[] segments;
    public GraphicElements2D graphicElements2D;

    public PolygonMesh(Vector2[] points, GraphicElements2D graphicElements2D) {
        this.vertices = points;
        int len = points.length;
        this.segments = new Segment[len];
        for (int i = 0; i < len; i++) {
            this.segments[i] = new Segment(points[i], points[(i + 1) % len]);
        }
        this.graphicElements2D = graphicElements2D;
    }

    public void draw(Vector2 position) {
        Renderer.drawPolygon(position, vertices, segments, graphicElements2D);
    }
}
