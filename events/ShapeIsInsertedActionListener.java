package events;

import java.util.EventListener;

public interface ShapeIsInsertedActionListener extends EventListener
{
    void shapeIsInserted(ShapeIsInsertedActionEvent event);
}
