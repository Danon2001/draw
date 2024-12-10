package events;

import java.awt.*;
import java.util.EventObject;

public class ChangedColorActionEvent extends EventObject
{

    private Color color;

    public ChangedColorActionEvent(Object source) {
        super(source);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
