package events;

import java.util.EventListener;

public interface EnableDeleteActionListener extends EventListener
{
    void deleteEnabled(EnableDeleteActionEvent event);
}
