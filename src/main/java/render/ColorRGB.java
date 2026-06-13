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

    public static int rgb(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static ColorRGB extractColor(int colorInt) {
        int r = (colorInt >> 16) & 0xFF;
        int g = (colorInt >> 8) & 0xFF;
        int b = colorInt & 0xFF;

        return new ColorRGB(r, g, b);
    }

    public static ColorRGB average(ColorRGB c1, ColorRGB c2) {
        int averageR = (int) Math.round((c1.r + c2.r) / 2d);
        int averageG = (int) Math.round((c1.g + c2.g) / 2d);
        int averageB = (int) Math.round((c1.b + c2.b) / 2d);

        return new ColorRGB(averageR, averageG, averageB);
    }

    public boolean equals(ColorRGB c) {
        return (r == c.r) && (g == c.g) && (b == c.b);
    }

    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ")";
    }

    public static ColorRGB interpolateColor(ColorRGB c1, ColorRGB c2, double alpha) {
        alpha = Math.clamp(alpha, 0, 1);
        int newR = c1.r + (int) Math.round((c2.r - c1.r) * alpha);
        int newG = c1.g + (int) Math.round((c2.g - c1.g) * alpha);
        int newB = c1.b + (int) Math.round((c2.b - c1.b) * alpha);

        return new ColorRGB(newR, newG, newB);
    }
}
