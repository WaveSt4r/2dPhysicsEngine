public class Color {
    int r = 0;
    int g = 0;
    int b = 0;

    public Color (int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getInt () {
        return (r << 16) | (g << 8) | b;
    }

    public static int rgb (int r, int g, int b) { return (r << 16) | (g << 8) | b; }

    public static Color extractColor(int colorInt) {
        int r = (colorInt >> 16) & 0xFF;
        int g = (colorInt >> 8) & 0xFF;
        int b = colorInt & 0xFF;

        return new Color(r, g, b);
    }
}
