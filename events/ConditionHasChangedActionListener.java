package events;

import java.util.EventListener;

public interface ConditionHasChangedActionListener extends EventListener
{
    void conditionHasChanged(ConditionHasChangedActionEvent event);
}
