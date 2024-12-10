package tools;

import controller.DrawingController;
import gui.ToolBox;
import shapes.Shape;
import shapes.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TextTool extends Tool
{
    private DrawingController controller;

    private ToolBox tools;

    private Shape newShape;


    public TextTool(DrawingController controller, ToolBox tools)
    {
        this.imageIcon = new ImageIcon("img/text.png");
        this.hintText = "Create text";
        this.controller = controller;
        this.tools = tools;
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point position = e.getPoint();
        try {
            String text = JOptionPane.showInputDialog("Text to be inserted:");
            if (text == null || text.isEmpty()) {
                throw new IllegalArgumentException("Empty text");
            }
            newShape = new Text(position.x, position.y, tools.getFontSize(), text);
            controller.colorShape(newShape, tools.getColor());
            controller.addShape(newShape);
            newShape = null;
        }
        catch (IllegalArgumentException exception) {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public boolean isFillable() {
        return false;
    }
}
