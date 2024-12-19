package events;

import java.util.EventObject;

public abstract class EnableActionEvent extends EventObject
{
    private boolean isEnable;


    public EnableActionEvent(Object source) {
        super(source);
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
