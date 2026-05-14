package render;

public class GraphicElements2D {
    public ColorRGB fillColor;
    public ColorRGB borderColor;
    public int borderWidth;

    public GraphicElements2D (ColorRGB fillColor, ColorRGB borderColor, int borderWidth) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    /*
    public render.GraphicElements2D (int fillColor, int borderColor, int borderWidth) {
        this.fillColor = Color.extractColor(fillColor);
        this.borderColor = Color.extractColor(borderColor);
        this.borderWidth = borderWidth;
    }
    */

    public GraphicElements2D (ColorRGB fillColor) {
        this.fillColor = fillColor;
        this.borderColor = new ColorRGB(0,0,0);
        this.borderWidth = 0;
    }

    /*
    public render.GraphicElements2D (int fillColor) {
        this.fillColor = Color.extractColor(fillColor);
        this.borderColor = new Color (0,0,0);
        this.borderWidth = 0;
    }
     */

}
