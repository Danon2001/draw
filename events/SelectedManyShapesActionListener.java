package events;

import java.util.EventListener;

public interface SelectedManyShapesActionListener extends EventListener
{
    void selectedManyShapes(SelectedManyShapesActionEvent event);
}
