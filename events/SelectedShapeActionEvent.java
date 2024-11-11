package events;

import java.util.EventObject;
import shapes.Shape;

public class SelectedShapeActionEvent extends EventObject {

    private Shape shape;

    public SelectedShapeActionEvent(Object source) {super(source);}

    public void setShape(Shape shape) {this.shape = shape;}

    public Shape getShape()
    {
        return shape;
    }

}
