package tools;

import controller.DrawingController;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class SelectTool extends Tool
{
    private DrawingController controller;

    protected Point lastPosition;

    private boolean isDragged = false;

    public SelectTool(DrawingController controller)
    {
        this.imageIcon = new ImageIcon("img/cursor.png");
        this.hintText = "Select and move shapes";
        this.controller = controller;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        Point position = e.getPoint();
        controller.moveSelectedShapes(new Point(position.x - lastPosition.x, position.y - lastPosition.y));
        lastPosition = position;
        isDragged = true;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point position = e.getPoint();
        Shape tmp = controller.getDrawing().getShapeAt(position);

        if (((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == 0)
                && !controller.getDrawing().getSelection().contains(tmp))
        {
            controller.clearSelection();
        }

        controller.addSelectionShape(tmp);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(isDragged)
        {
            controller.completeMoveSelectedShapes();
            isDragged = false;
        }
    }

    public void mouseMoved(MouseEvent e) {
        lastPosition = e.getPoint();
    }

    @Override
    public boolean isFillable() {
        return true;
    }

}
