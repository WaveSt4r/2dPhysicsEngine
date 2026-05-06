public class BoxMesh implements Mesh {
    double width;
    double height;
    GraphicElements2D graphicElements2D;

    public BoxMesh(double width, double height, GraphicElements2D graphicElements2D) {
        this.width = width;
        this.height = height;
        this.graphicElements2D = graphicElements2D;
    }

    public void draw (Vector2 position) {
        Renderer.drawRect((int) Math.round(position.x), (int) Math.round(position.y), (int) Math.round(width), (int) Math.round(height), graphicElements2D);
    }
}
