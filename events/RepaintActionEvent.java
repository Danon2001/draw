package events;

import java.util.EventObject;

/* Создаем класс для того, чтобы представлять событие, связанное с перерисовкой (repaint) */
public class RepaintActionEvent extends EventObject {

    public RepaintActionEvent(Object source) {super(source);}
}
