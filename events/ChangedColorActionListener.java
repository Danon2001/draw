package events;

import java.util.EventListener;

public interface ChangedColorActionListener extends EventListener
{
    void changedColor(ChangedColorActionEvent event);

}
