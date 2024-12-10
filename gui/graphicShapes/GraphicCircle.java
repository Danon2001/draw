package gui.graphicShapes;

import shapes.Circle;

import java.awt.*;

public class GraphicCircle extends GraphicFillableShape
{

    public GraphicCircle(Circle circle) {
        super(circle);
    }

    @Override
    public void drawFilled(Graphics g)
    {
        g.fillOval(shape.getPosition().x, shape.getPosition().y, shape.getSize().x, shape.getSize().y);
    }

    @Override
    public void drawNonFilled(Graphics g)
    {
        g.drawOval(shape.getPosition().x, shape.getPosition().y, shape.getSize().x, shape.getSize().y);
    }
}
