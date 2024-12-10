package tools;

import controller.DrawingController;
import gui.ToolBox;
import shapes.Line;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends Tool
{
    private DrawingController controller;

    private ToolBox tools;

    private Shape newShape;


    public LineTool(DrawingController controller, ToolBox tools)
    {
        this.imageIcon = new ImageIcon("img/line.png");
        this.hintText = "Draw lines";
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
        newShape = new Line(position.x, position.y);
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
        return false;
    }
}
