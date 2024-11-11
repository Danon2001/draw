package actions;

import shapes.Shape;
import java.awt.Point;

/* Класс создан для реализации действия изменения размера (или изменения позиции конечной точки) объекта Shape.
 Он инкапсулирует всю информацию, необходимую для изменения размера фигуры, и позволяет это действие выполнить,
  отменить или объединить с аналогичными действиями. */
public class ChangeSizeAction implements DrawAction, UnityAction {

    Shape shape;

    Point newPoint2;
    Point oldPoint2;


    public ChangeSizeAction(Shape shape, Point point2)
    {
        this.shape = shape;
        this.newPoint2 = point2;
        this.oldPoint2 = shape.getPoint2();
    }

    @Override
    public void execute() {
        shape.setPoint2(newPoint2);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void redo() {
        this.execute();
    }

    @Override
    public void undo() {
        shape.setPoint2(oldPoint2);
    }

    @Override
    public boolean unity(UnityAction otherObject) {

        if (!(otherObject instanceof ChangeSizeAction))
        {
            return false;
        }

        ChangeSizeAction otherCommand = (ChangeSizeAction) otherObject;

        if (!this.shape.equals(otherCommand.shape))
        {
            return false;
        }

        this.newPoint2 = otherCommand.newPoint2;

        return true;
    }
}
