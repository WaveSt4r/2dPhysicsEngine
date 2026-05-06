import java.awt.image.BufferedImage;

public abstract class Renderer {
    public static BufferedImage img;
    public static int imgWidth;
    public static int imgHeight;

    public static void initialize (BufferedImage image) {
        img = image;
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();
    }

    public static void setPixel (int x, int y, Color color) {
        if (x<imgWidth && y<imgHeight && x>=0 && y>=0) {
            img.setRGB(x%imgWidth,y%imgHeight,color.getInt());
        }
    }

    public static void drawRect (int x, int y, int width, int height, GraphicElements2D graphicElements2D) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(graphicElements2D.borderColor == null) {
                    setPixel(i + x, j + y, graphicElements2D.fillColor);
                } else {
                    if (i < graphicElements2D.borderWidth || i > width-graphicElements2D.borderWidth || j < graphicElements2D.borderWidth || j > height-graphicElements2D.borderWidth){
                        setPixel(i + x, j + y, graphicElements2D.borderColor);
                        continue;
                    }
                    setPixel(i + x, j + y, graphicElements2D.fillColor);
                }
            }
        }
    }


    public static void drawCircle (int x, int y, int radius, GraphicElements2D graphicElements2D) {
        for (int i = x-radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                int distance = (x - i) * (x - i) + (y - j) * (y - j);
                if (distance <= radius*radius) {
                    if(distance > (radius - graphicElements2D.borderWidth)*(radius - graphicElements2D.borderWidth)) {
                        setPixel(i, j, graphicElements2D.borderColor);
                    } else {
                        setPixel(i, j, graphicElements2D.fillColor);
                    }

                }
            }
        }
    }



    public static void rainbow() {
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int r = i * 255 / imgWidth;
                int g = j * 255 / imgHeight;
                int b = 128;
                Color color = new Color(i * 255 / imgWidth, j * 255 / imgHeight, 128);
                setPixel(i, j, color);
            }
        }
    }

    public static void clearToColor (Color color) {
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                setPixel(i, j, color);
            }
        }
    }
}
