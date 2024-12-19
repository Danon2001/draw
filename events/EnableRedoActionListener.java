package events;

import java.util.EventListener;

public interface EnableRedoActionListener extends EventListener
{
    void redoEnabled(EnableRedoActionEvent event);
}
