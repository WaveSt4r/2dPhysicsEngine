package render;

import core.Game;
import math.Vector2;

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
            img.setRGB(x%imgWidth,y%imgHeight, colorRGB.toInt());
        }
    }

    public static void drawRect (int x, int y, int width, int height, GraphicElements2D graphicElements2D) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
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


    public static void drawCircle (Vector2 position, int radius, GraphicElements2D graphicElements2D) {
        int x = (int) Math.round(position.x);
        int y = (int) Math.round(position.y);
        for (int j = y-radius-1; j <= y+radius+1; j++) {
            for (int i = x-radius-1; i <= x+radius+1; i++) {
                double distance = (position.x - i) * (position.x - i) + (position.y - j) * (position.y - j);
                double difference = distance - radius*radius;

                if (distance <= radius*radius) {
                    if(distance > (radius - graphicElements2D.borderWidth)*(radius - graphicElements2D.borderWidth)) {
                        setPixel(i, j, graphicElements2D.borderColor);
                    } else {
                        setPixel(i, j, graphicElements2D.fillColor);
                    }
                }

                /*if (difference > -1 && difference < 1 && difference != 0) {
                    double differenceSqrt = Math.sqrt(distance) - radius;
                    // TODO If it works, change later
                    ColorRGB nearColor = Game.backgroundColor;
                    ColorRGB interpolatedColor = ColorRGB.interpolateColor(nearColor, graphicElements2D.fillColor, -differenceSqrt);
                    setPixel(i, j, interpolatedColor);
                    System.out.println("NearColor: " + nearColor + "\tBallColor: (255, 255, 255)\tDifference: " + differenceSqrt + "\tInterpolated Color: " + interpolatedColor);
                }*/
            }
        }
    }



    public static void rainbow() {
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                int r = i * 255 / imgWidth;
                int g = j * 255 / imgHeight;
                int b = 128;
                ColorRGB colorRGB = new ColorRGB(i * 255 / imgWidth, j * 255 / imgHeight, 128);
                setPixel(i, j, colorRGB);
            }
        }
    }

    public static void clearToColor (ColorRGB colorRGB) {
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                setPixel(i, j, colorRGB);
            }
        }
    }

    public static void fxaa () {
        for (int i = 1; i < imgWidth-1; i++) {
            for (int j = 1; j < imgHeight-1; j++) {
                ColorRGB PColor = ColorRGB.extractColor(img.getRGB(i,j));
                ColorRGB TopPColor = ColorRGB.extractColor(img.getRGB(i,j-1));
                ColorRGB TopRightPColor = ColorRGB.extractColor(img.getRGB(i+1,j-1));
                ColorRGB TopLeftPColor = ColorRGB.extractColor(img.getRGB(i-1,j-1));
                ColorRGB RightColor = ColorRGB.extractColor(img.getRGB(i+1,j));
                ColorRGB LeftColor = ColorRGB.extractColor(img.getRGB(i-1,j));

                if (PColor.equals(TopPColor) || PColor.equals(RightColor) || PColor.equals(LeftColor)) {
                    continue;
                }
                if (PColor.equals(TopRightPColor)) {
                    ColorRGB avg = ColorRGB.average(PColor, TopPColor);
                    ColorRGB topavg = ColorRGB.average(avg, TopPColor);
                    ColorRGB selfavg = ColorRGB.average(avg, PColor);

                    img.setRGB(i,j-1, topavg.toInt());
                    img.setRGB(i, j, selfavg.toInt());
                }
                if (PColor.equals(TopLeftPColor)) {
                    ColorRGB avg = ColorRGB.average(PColor, TopPColor);
                    ColorRGB topavg = ColorRGB.average(avg, TopPColor);
                    ColorRGB selfavg = ColorRGB.average(avg, PColor);

                    img.setRGB(i,j-1, topavg.toInt());
                    img.setRGB(i, j, selfavg.toInt());
                }
            }
        }
    }
}
