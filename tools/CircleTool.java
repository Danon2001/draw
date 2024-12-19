package tools;

import controller.DrawingController;
import shapes.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class CircleTool extends FillableShapeTool
{
    public CircleTool(DrawingController controller)
    {
        super(controller, new ImageIcon("img/circle.png"), "Draw circles and ellipses");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.changeSizeShape(shape, e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point position = e.getPoint();
        shape = shapeFactory.createCircle(position, controller.getToolBox().getFill(), controller.getToolBox().getColor());
        controller.addShape(shape);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shape = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public CircleTool(Circle circle) {
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
