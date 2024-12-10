package events;

import java.util.EventListener;

public interface ShapeHasBeenDeletedActionListener extends EventListener
{
    void shapeHasBeenDeleted(ShapeHasBeenDeletedActionEvent event);
}
