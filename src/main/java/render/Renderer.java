package render;

import core.Game;
import core.Launcher;
import math.Segment;
import math.Vector2;

import java.awt.image.BufferedImage;

public abstract class Renderer {
    public static BufferedImage img;
    public static int imgWidth;
    public static int imgHeight;

    public static void initialize(BufferedImage image) {
        img = image;
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();
    }

    public static void setPixel(double x, double y, ColorRGB colorRGB) {
        setPixel((int) Math.round(x), (int) Math.round(y), colorRGB);
    }

    public static void setPixel(int x, int y, ColorRGB colorRGB) {
        if (x < imgWidth && y < imgHeight && x >= 0 && y >= 0) {
            img.setRGB(x, y, colorRGB.toInt());
        }
    }

    public static void setPixel(double x, double y, ColorRGB colorRGB, double opacity) {
        setPixel((int) Math.round(x), (int) Math.round(y), colorRGB, opacity);
    }

    public static void setPixel(int x, int y, ColorRGB colorRGB, double opacity) {
        if (x < imgWidth && y < imgHeight && x >= 0 && y >= 0) {
            img.setRGB(x, y, ColorRGB.interpolateColor(ColorRGB.extractColor(img.getRGB(x, y)), colorRGB, opacity).toInt());
        }
    }

    public static void drawRect(int x, int y, int width, int height, GraphicElements2D graphicElements2D) {
        for (int j = 0; Math.abs(j) < Math.abs(height); j += (int) Math.signum(height)) {
            for (int i = 0; Math.abs(i) < Math.abs(width); i += (int) Math.signum(width)) {
                if (graphicElements2D.borderColor == null) {
                    setPixel(i + x, j + y, graphicElements2D.fillColor);
                } else {
                    if (i < graphicElements2D.borderWidth || i > width - graphicElements2D.borderWidth || j < graphicElements2D.borderWidth || j > height - graphicElements2D.borderWidth) {
                        setPixel(i + x, j + y, graphicElements2D.borderColor);
                        continue;
                    }
                    setPixel(i + x, j + y, graphicElements2D.fillColor);
                }
            }
        }
    }


    public static void drawCircle(Vector2 position, int radius, GraphicElements2D graphicElements2D) {
        double cx = position.x;
        double cy = position.y;
        double rOuter = radius;
        double rInner = radius - graphicElements2D.borderWidth;

        int yMin = (int) Math.floor(cy - rOuter);
        int yMax = (int) Math.ceil(cy + rOuter);

        // Loop through rows sequentially (O(R) complexity)
        for (int j = yMin; j <= yMax; j++) {
            // Calculate the vertical distance from the sub-pixel center to the row center
            double dy = j - cy;
            double dySq = dy * dy;

            // 1. Calculate horizontal bounds for the Outer Circle
            double remainingOuterSq = (rOuter * rOuter) - dySq;
            if (remainingOuterSq < 0) continue; // Row is completely outside the circle
            double dxOuter = Math.sqrt(remainingOuterSq);
            int xMinOuter = (int) Math.ceil(cx - dxOuter);
            int xMaxOuter = (int) Math.floor(cx + dxOuter);

            // 2. Calculate horizontal bounds for the Inner Circle (Fill Area)
            double remainingInnerSq = (rInner * rInner) - dySq;
            int xMinInner = Integer.MAX_VALUE;
            int xMaxInner = Integer.MIN_VALUE;
            if (remainingInnerSq >= 0) {
                double dxInner = Math.sqrt(remainingInnerSq);
                xMinInner = (int) Math.ceil(cx - dxInner);
                xMaxInner = (int) Math.floor(cx + dxInner);
            }

            // 3. Render the specific segments for this row
            for (int i = xMinOuter; i <= xMaxOuter; i++) {
                // If it falls within the inner circle limits, it's Fill
                if (i >= xMinInner && i <= xMaxInner) {
                    setPixel(i, j, graphicElements2D.fillColor);
                } else {
                    // Otherwise, it belongs to the Border
                    setPixel(i, j, graphicElements2D.borderColor);
                }
            }

            // 4. OPTIONAL: Anti-alias the outer edge pixels for perfect smoothness
            // (Uncomment this section if you want blurry/smooth sub-pixel edges)
            int i = xMinOuter - 1;
            double dist = Math.sqrt((cx - i) * (cx - i) + (cy - j) * (cy - j));
            double delta = radius - dist;

            // If the pixel is within 1 pixel distance of the exact edge
            if (delta > -0.5 && delta < 0.5) {
                double alphaFactor = delta + 0.5; // Maps coverage to a 0.0 -> 1.0 range
                setPixel(i, j, graphicElements2D.borderColor, alphaFactor);
            }

            i = xMaxOuter + 1;
            dist = Math.sqrt((cx - i) * (cx - i) + (cy - j) * (cy - j));
            delta = radius - dist;

            // If the pixel is within 1 pixel distance of the exact edge
            if (delta > -0.5 && delta < 0.5) {
                double alphaFactor = delta + 0.5; // Maps coverage to a 0.0 -> 1.0 range
                setPixel(i, j, graphicElements2D.borderColor, alphaFactor);
            }
        }
    }

    public static void drawPolygon(Vector2 position, Vector2[] vertices, Segment[] segments, GraphicElements2D g) {
        double minY = vertices[0].y, maxY = vertices[0].y;

        for (Vector2 v : vertices) {
            minY = Math.min(minY, v.y);
            maxY = Math.max(maxY, v.y);
        }

        int yStart = (int) Math.floor(minY);
        int yEnd = (int) Math.ceil(maxY);

        double bw = g.borderWidth;

        for (int y = yStart; y <= yEnd; y++) {

            double leftX = Double.POSITIVE_INFINITY;
            double rightX = Double.NEGATIVE_INFINITY;

            int intersections = 0;

            for (Segment s : segments) {

                double y1 = s.startPos.y, y2 = s.endPos.y;

                if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {

                    double t = (y - y1) / (y2 - y1);
                    double x = s.startPos.x + t * (s.endPos.x - s.startPos.x);

                    leftX = Math.min(leftX, x);
                    rightX = Math.max(rightX, x);

                    intersections++;
                }
            }

            if (intersections < 2) continue;

            int xStart = (int) Math.floor(leftX);
            int xEnd = (int) Math.ceil(rightX);

            boolean isTopOrBottom = (y - yStart <= bw) || (yEnd - y <= bw);

            for (int x = xStart; x <= xEnd; x++) {

                boolean border =
                        isTopOrBottom ||
                                x - leftX <= bw ||
                                rightX - x <= bw;

                setPixel(
                        (int) (x + position.x),
                        (int) (y + position.y),
                        border ? g.borderColor : g.fillColor
                );
            }
        }

//        for (Segment s : segments) {
//            Segment seg = new Segment(Vector2.sum(position, s.startPos),  Vector2.sum(position, s.endPos));
//            drawLine(seg, g);
//        }

    }

    public static void drawLine(Segment segment, GraphicElements2D graphicElements2D) {
        double halfBorderWidth = graphicElements2D.borderWidth / 2d;
        Vector2 normal = segment.getNormal();
        Vector2 delta = segment.getDelta();
        double sx = segment.startPos.x - normal.x * halfBorderWidth;
        double sy = segment.startPos.y - normal.y * halfBorderWidth;
        double ex = segment.startPos.x + normal.x * halfBorderWidth;
        double ey = segment.startPos.y + normal.y * halfBorderWidth;
        double ddx = ex - sx;
        double ddy = ey - sy;

        double widthSteps = Math.max(Math.abs(ddx), Math.abs(ddy));
        double lengthSteps = Math.max(Math.abs(delta.x), Math.abs(delta.y));

        if (widthSteps != 0 && lengthSteps != 0) {
            double widthStepX = ddx / widthSteps;
            double heightStepY = ddy / widthSteps;

            double lengthStepX = delta.x / lengthSteps;
            double lengthStepY = delta.y / lengthSteps;

            double x0 = sx;
            double y0 = sy;

            double x, y;

            for (int i = 0; i <= widthSteps; i++) {
                x = x0;
                y = y0;
                for (int j = 0; j <= lengthSteps; j++) {
                    setPixel(x, y, graphicElements2D.borderColor);

                    x += lengthStepX;
                    y += lengthStepY;
                }

                x0 += widthStepX;
                y0 += heightStepY;
            }
        }
    }

    public static void rainbow() {
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                ColorRGB colorRGB = new ColorRGB(i * 255 / imgWidth, j * 255 / imgHeight, 128);
                setPixel(i, j, colorRGB);
            }
        }
    }

    public static void clearToColor(ColorRGB colorRGB) {
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                setPixel(i, j, colorRGB);
            }
        }
    }

    public static void refresh() {
        clearToColor(Game.backgroundColor);
        Game.currentScene.draw();
        Launcher.repaintPanel();
        try {
            Thread.sleep(Game.DT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fxaa() {
        for (int i = 1; i < imgWidth - 1; i++) {
            for (int j = 1; j < imgHeight - 1; j++) {
                ColorRGB PColor = ColorRGB.extractColor(img.getRGB(i, j));
                ColorRGB TopPColor = ColorRGB.extractColor(img.getRGB(i, j - 1));
                ColorRGB TopRightPColor = ColorRGB.extractColor(img.getRGB(i + 1, j - 1));
                ColorRGB TopLeftPColor = ColorRGB.extractColor(img.getRGB(i - 1, j - 1));
                ColorRGB RightColor = ColorRGB.extractColor(img.getRGB(i + 1, j));
                ColorRGB LeftColor = ColorRGB.extractColor(img.getRGB(i - 1, j));

                if (PColor.equals(TopPColor) || PColor.equals(RightColor) || PColor.equals(LeftColor)) {
                    continue;
                }
                if (PColor.equals(TopRightPColor)) {
                    ColorRGB avg = ColorRGB.average(PColor, TopPColor);
                    ColorRGB topAvg = ColorRGB.average(avg, TopPColor);
                    ColorRGB selfAvg = ColorRGB.average(avg, PColor);

                    img.setRGB(i, j - 1, topAvg.toInt());
                    img.setRGB(i, j, selfAvg.toInt());
                }
                if (PColor.equals(TopLeftPColor)) {
                    ColorRGB avg = ColorRGB.average(PColor, TopPColor);
                    ColorRGB topAvg = ColorRGB.average(avg, TopPColor);
                    ColorRGB selfAvg = ColorRGB.average(avg, PColor);

                    img.setRGB(i, j - 1, topAvg.toInt());
                    img.setRGB(i, j, selfAvg.toInt());
                }
            }
        }
    }
}
