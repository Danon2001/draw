package events;

import java.util.EventListener;

public interface FillingChangedActionListener extends EventListener
{
    void fillingChanged(FillingChangedActionEvent event);
}
