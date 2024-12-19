package events;

import java.util.EventListener;

public interface EnableClearActionListener extends EventListener
{
    void clearEnabled(EnableClearActionEvent event);
}
