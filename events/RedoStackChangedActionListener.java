package events;

import java.util.EventListener;

public interface RedoStackChangedActionListener extends EventListener
{
    void redoStackChanged(RedoStackChangedActionEvent event);
}
