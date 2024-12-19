package actions;

import shapes.Shape;

public abstract class ShapeAction
{
    protected Shape shape;

    public ShapeAction(Shape shape) {
        this.shape = shape;
    }
}
