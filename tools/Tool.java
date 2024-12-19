package tools;

import controller.DrawingController;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Tool
{
    protected DrawingController controller;

    protected ImageIcon imageIcon;

    protected String hintText;

    public Tool(){}

    public Tool(DrawingController controller, ImageIcon imageIcon, String hintText)
    {
        this.controller = controller;
        this.imageIcon = imageIcon;
        this.hintText = hintText;
    }

    public abstract void mouseDragged(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String getHintText() {
        return hintText;
    }

}
