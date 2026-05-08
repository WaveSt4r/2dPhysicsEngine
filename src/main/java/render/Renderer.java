package render;

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

    public static void setPixel (int x, int y, ColorRGB colorRGB) {
        if (x<imgWidth && y<imgHeight && x>=0 && y>=0) {
            img.setRGB(x%imgWidth,y%imgHeight, colorRGB.getInt());
        }
    }

    public static void drawRect (int x, int y, int width, int height, GraphicElements2D graphicElements2D) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(graphicElements2D.borderColorRGB == null) {
                    setPixel(i + x, j + y, graphicElements2D.fillColorRGB);
                } else {
                    if (i < graphicElements2D.borderWidth || i > width-graphicElements2D.borderWidth || j < graphicElements2D.borderWidth || j > height-graphicElements2D.borderWidth){
                        setPixel(i + x, j + y, graphicElements2D.borderColorRGB);
                        continue;
                    }
                    setPixel(i + x, j + y, graphicElements2D.fillColorRGB);
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
                        setPixel(i, j, graphicElements2D.borderColorRGB);
                    } else {
                        setPixel(i, j, graphicElements2D.fillColorRGB);
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
                ColorRGB colorRGB = new ColorRGB(i * 255 / imgWidth, j * 255 / imgHeight, 128);
                setPixel(i, j, colorRGB);
            }
        }
    }

    public static void clearToColor (ColorRGB colorRGB) {
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                setPixel(i, j, colorRGB);
            }
        }
    }
}
