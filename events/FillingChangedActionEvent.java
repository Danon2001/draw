package events;

import java.util.EventObject;

public class FillingChangedActionEvent extends EventObject
{

    private boolean fill;


    public FillingChangedActionEvent(Object source) {
        super(source);
    }

    public boolean getFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
