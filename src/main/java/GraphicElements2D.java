public class GraphicElements2D {
    public Color fillColor;
    public Color borderColor;
    public int borderWidth;

    public GraphicElements2D (Color fillColor, Color borderColor, int borderWidth) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    /*
    public GraphicElements2D (int fillColor, int borderColor, int borderWidth) {
        this.fillColor = Color.extractColor(fillColor);
        this.borderColor = Color.extractColor(borderColor);
        this.borderWidth = borderWidth;
    }
    */

    public GraphicElements2D (Color fillColor) {
        this.fillColor = fillColor;
        this.borderColor = new Color(0,0,0);
        this.borderWidth = 0;
    }

    /*
    public GraphicElements2D (int fillColor) {
        this.fillColor = Color.extractColor(fillColor);
        this.borderColor = new Color (0,0,0);
        this.borderWidth = 0;
    }
     */

}
