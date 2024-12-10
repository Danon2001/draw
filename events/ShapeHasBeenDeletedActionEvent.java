package events;

import shapes.Shape;

import java.util.EventObject;

public class ShapeHasBeenDeletedActionEvent extends EventObject
{
    private Shape shape;


    public ShapeHasBeenDeletedActionEvent(Object source) {
        super(source);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
