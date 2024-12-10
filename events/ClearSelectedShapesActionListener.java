package events;

import java.util.EventListener;

public interface ClearSelectedShapesActionListener extends EventListener
{
    void clearSelectedShapes(ClearSelectedShapesActionEvent event);
}
