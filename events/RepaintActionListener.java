package events;

import java.util.EventListener;

public interface RepaintActionListener extends EventListener {

    void repaint(RepaintActionEvent event);
}
