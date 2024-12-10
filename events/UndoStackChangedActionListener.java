package events;

import java.util.EventListener;

public interface UndoStackChangedActionListener extends EventListener
{
    void undoStackChanged(UndoStackChangedActionEvent event);
}
