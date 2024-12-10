package events;

import java.util.EventObject;

public class UndoStackChangedActionEvent extends EventObject {

    private boolean canUndo;


    public UndoStackChangedActionEvent(Object source) {
        super(source);
    }

    public boolean canUndo() {
        return canUndo;
    }

    public void setCanUndo(boolean canUndo) {
        this.canUndo = canUndo;
    }
}
