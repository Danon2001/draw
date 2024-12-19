package tools;

import controller.DrawingController;
import shapes.FillableShape;

import javax.swing.*;
import java.awt.*;

public abstract class FillableShapeTool extends ShapeTool
{
    public FillableShapeTool(DrawingController controller, ImageIcon imageIcon, String tipText)
    {
        super(controller, imageIcon, tipText);
    }

    public FillableShapeTool(FillableShape fillableShape) {
        super(fillableShape);
    }

    public abstract void drawFilled(Graphics g);

    public abstract void drawNonFilled(Graphics g);

    public void drawShape(Graphics g)
    {
        ((Graphics2D) g).setStroke(new BasicStroke((float) strokeWidth));

        if (((FillableShape)shape).getFilled())
        {
            drawFilled(g);
        } else
        {
            drawNonFilled(g);
        }
    }
}
