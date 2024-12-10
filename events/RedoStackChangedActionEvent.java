package events;

import java.util.EventObject;

public class RedoStackChangedActionEvent extends EventObject
{
    private boolean canRedo;


    public RedoStackChangedActionEvent(Object source) {
        super(source);
    }

    public boolean canRedo() {
        return canRedo;
    }

    public void setCanRedo(boolean canRedo) {
        this.canRedo = canRedo;
    }
}
