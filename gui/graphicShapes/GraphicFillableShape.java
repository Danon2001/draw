package gui.graphicShapes;

import shapes.FillableShape;

import java.awt.*;

public abstract class GraphicFillableShape extends GraphicShape
{
    public GraphicFillableShape(FillableShape fillableShape) {
        super(fillableShape);
    }

    public abstract void drawFilled(Graphics g);

    public abstract void drawNonFilled(Graphics g);

    public void drawShape(Graphics g)
    {
        ((Graphics2D) g).setStroke(new BasicStroke((float) strokeWidth));

        if (((FillableShape)shape).getFilled()) {
            drawFilled(g);
        } else {
            drawNonFilled(g);
        }
    }
}
