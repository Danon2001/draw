package events;

import java.util.EventListener;

public interface EnableUndoActionListener extends EventListener
{
    void undoEnabled(EnableUndoActionEvent event);
}
