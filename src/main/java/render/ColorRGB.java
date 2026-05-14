package render;

public class ColorRGB {
    int r;
    int g;
    int b;

    public ColorRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int toInt() {
        return (r << 16) | (g << 8) | b;
    }

    public static int rgb (int r, int g, int b) { return (r << 16) | (g << 8) | b; }

    public static ColorRGB extractColor(int colorInt) {
        int r = (colorInt >> 16) & 0xFF;
        int g = (colorInt >> 8) & 0xFF;
        int b = colorInt & 0xFF;

        return new ColorRGB(r, g, b);
    }

    public static ColorRGB average (ColorRGB c1, ColorRGB c2) {
        int averageR = (int) Math.round((c1.r + c2.r)/2d);
        int averageG = (int) Math.round((c1.g + c2.g)/2d);
        int averageB = (int) Math.round((c1.b + c2.b)/2d);

        return new ColorRGB(averageR,averageG,averageB);
    }

    public boolean equals(ColorRGB c) {
        return (r == c.r) && (g == c.g) && (b == c.b);
    }

    @Override
    public String toString () {
        return "(" + r + ", " + g + ", " + b + ")";
    }

    public static ColorRGB interpolateColor (ColorRGB c1, ColorRGB c2, double percentage) {
        boolean addToFirstColor = (percentage>=0);
        percentage = Math.abs(percentage);

        int rDifference = Math.abs(c2.r - c1.r);
        int gDifference = Math.abs(c2.g - c1.g);
        int bDifference = Math.abs(c2.b - c1.b);

        int rScaled = (int) Math.round(rDifference * percentage);
        int gScaled = (int) Math.round(gDifference * percentage);
        int bScaled = (int) Math.round(bDifference * percentage);

        int newR = (addToFirstColor) ? c1.r + rScaled : c2.r - rScaled;
        int newG = (addToFirstColor) ? c1.g + gScaled : c2.g - gScaled;
        int newB = (addToFirstColor) ? c1.b + bScaled : c2.b - bScaled;

        return new ColorRGB(newR, newG, newB);
    }
}
