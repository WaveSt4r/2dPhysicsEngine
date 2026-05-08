package render;

public class GraphicElements2D {
    public ColorRGB fillColorRGB;
    public ColorRGB borderColorRGB;
    public int borderWidth;

    public GraphicElements2D (ColorRGB fillColorRGB, ColorRGB borderColorRGB, int borderWidth) {
        this.fillColorRGB = fillColorRGB;
        this.borderColorRGB = borderColorRGB;
        this.borderWidth = borderWidth;
    }

    /*
    public render.GraphicElements2D (int fillColor, int borderColor, int borderWidth) {
        this.fillColor = Color.extractColor(fillColor);
        this.borderColor = Color.extractColor(borderColor);
        this.borderWidth = borderWidth;
    }
    */

    public GraphicElements2D (ColorRGB fillColorRGB) {
        this.fillColorRGB = fillColorRGB;
        this.borderColorRGB = new ColorRGB(0,0,0);
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
