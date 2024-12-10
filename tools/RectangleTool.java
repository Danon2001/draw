package tools;

import controller.DrawingController;
import gui.ToolBox;
import shapes.Rectangle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleTool extends Tool
{
    private DrawingController controller;

    private ToolBox tools;

    private Shape newShape;


    public RectangleTool(DrawingController controller, ToolBox tools)
    {
        this.imageIcon = new ImageIcon("img/rectangle.png");
        this.hintText = "Draw squares and rectangles";
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
        newShape = new Rectangle(position.x, position.y, tools.getFill());
        controller.colorShape(newShape, tools.getColor());
        controller.addShape(newShape);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        newShape = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public boolean isFillable() {
        return true;
    }
}
