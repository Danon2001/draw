package tools;

import controller.DrawingController;
import gui.ToolBox;
import shapes.Circle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CircleTool extends Tool
{

    private DrawingController controller;

    private ToolBox tools;

    private Shape newShape;


    public CircleTool(DrawingController controller, ToolBox tools)
    {
        this.imageIcon = new ImageIcon("img/circle.png");
        this.hintText = "Draw circles and ellipses";
        this.controller = controller;
        this.tools = tools;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.changeSizeShape(newShape, e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point position = e.getPoint();
        newShape = new Circle(position.x, position.y, tools.getFill());
        controller.colorShape(newShape, tools.getColor());
        controller.addShape(newShape);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        newShape = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public boolean isFillable() {
        return true;
    }
}
