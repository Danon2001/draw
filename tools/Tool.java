package tools;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Tool
{
    protected ImageIcon imageIcon;

    protected String hintText;

    public Tool(){}

    public abstract void mouseDragged(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public abstract boolean isFillable();
}
