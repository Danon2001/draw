package events;

import java.util.EventListener;

public interface SelectedShapeActionListener extends EventListener {

    void selectedShape(SelectedShapeActionEvent event);

}
