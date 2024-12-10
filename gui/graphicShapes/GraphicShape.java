package gui.graphicShapes;

import shapes.Shape;

import java.awt.*;

public abstract class GraphicShape {

    protected Shape shape;

    private static final double DEFAULT_STROKE_WIDTH = 2;

    protected double strokeWidth;

    public GraphicShape(Shape shape)
    {
        this.shape = shape;
        strokeWidth = DEFAULT_STROKE_WIDTH;
    }

    public void draw(Graphics g)
    {
        g.setColor(shape.getColor());
        drawShape(g);
        if (shape.getSelected())
        {
            drawSelectionIndicator(g);
        }
    }

    public void drawSelectionIndicator(Graphics g) {

        ((Graphics2D) g).setStroke(new BasicStroke((float) 1.0));
        g.setColor(new Color(255, 0, 255));

        int len = 10;
        int off = 5;

        Point p1 = shape.getPosition();
        Point p2 = new Point(shape.getPosition().x + shape.getSize().x, shape.getPosition().y
                + shape.getSize().y);

        g.drawPolyline(
                // left up
                new int[] { p1.x - off, p1.x - off, p1.x - off + len },
                new int[] { p1.y - off + len, p1.y - off, p1.y - off }, 3);

        g.drawPolyline(
                // right down
                new int[] { p2.x + off - len, p2.x + off, p2.x + off },
                new int[] { p2.y + off, p2.y + off, p2.y + off - len }, 3);

        g.drawPolyline(
                // right up
                new int[] { p2.x + off - len, p2.x + off, p2.x + off },
                new int[] { p1.y - off, p1.y - off, p1.y - off + len }, 3);

        g.drawPolyline(
                // left down
                new int[] { p1.x - off, p1.x - off, p1.x - off + len },
                new int[] { p2.y + off - len, p2.y + off, p2.y + off }, 3);

    }

    public abstract void drawShape(Graphics g);
}
