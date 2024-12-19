package events;

import java.util.EventListener;

public interface EnableSelectAllActionListener extends EventListener
{
    void selectAllEnabled(EnableSelectAllActionEvent event);
}
