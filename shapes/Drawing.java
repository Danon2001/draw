package shapes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import events.*;


public class Drawing implements Iterable<Shape>
{
    private ArrayList<Shape> shapes;

    private Dimension sizeCanvas;
    private Selection selection;

    public Drawing(Dimension size)
    {
        shapes = new ArrayList<Shape>(0);

        selection = new Selection(this);
        this.sizeCanvas = size;
    }

    public Shape getShapeAt(Point p)
    {
        int index = shapes.size() - 1;
        while (index >= 0)
        {
            if (shapes.get(index).includes(p))
            {
                return shapes.get(index);
            }
            index--;
        }
        return null;

    }

    public void insertShape(Shape s)
    {
        shapes.add(s);
        this.fireShapeIsInserted(s);
        for(ConditionHasChangedActionListener listener: repaintActionListener)
        {
            s.addRepaintActionListener(listener);
        }
        this.fireConditionHasChanged();
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public void listShapes()
    {
        System.out.println("---");
        for (Shape s : shapes)
        {
            System.out.println(s);
        }
        System.out.println("---");
    }

    public void lower(Shape s)
    {
        int index = shapes.indexOf(s);
        if (index < shapes.size() - 1)
        {
            shapes.remove(s);
            shapes.add(index, s);
        }
    }

    public int nShapes() {
        return shapes.size();
    }

    public void raise(Shape s)
    {
        int index = shapes.indexOf(s);
        if (index > 0)
        {
            shapes.remove(s);
            shapes.add(--index, s);
        }
    }

    public void removeShape(Shape s)
    {
        for(ConditionHasChangedActionListener listener: repaintActionListener)
        {
            s.removeRepaintActionListener(listener);
        }
        this.fireShapeHasBeenDeleted(s);
        shapes.remove(s);
        this.fireConditionHasChanged();
    }

    public Dimension getSize() {return sizeCanvas;}

    public Selection getSelection() {return selection;}

    public void clearSelection() {selection.empty();}

    public void selectAllShapes()
    {
        for (Shape s : shapes)
        {
            selection.add(s);
        }
    }



    // ------------------------------- EVENTS ---------------------------------

    private ArrayList<ConditionHasChangedActionListener> repaintActionListener = new ArrayList<>();

    public void addRepaintActionListener(ConditionHasChangedActionListener listener)
    {
        repaintActionListener.add(listener);
        selection.addRepaintActionListener(listener);
    }

    public void removeRepaintActionListener(ConditionHasChangedActionListener listener)
    {
        repaintActionListener.remove(listener);
        selection.removeRepaintActionListener(listener);
    }

    private void fireConditionHasChanged()
    {
        for(ConditionHasChangedActionListener listener: repaintActionListener)
        {
            ConditionHasChangedActionEvent event = new ConditionHasChangedActionEvent(listener);
            listener.conditionHasChanged(event);
        }
    }


    private ArrayList<ShapeIsInsertedActionListener> shapeIsInsertedActionListeners = new ArrayList<>();

    public void addShapeIsInsertedActionListener(ShapeIsInsertedActionListener listener)
    {
        shapeIsInsertedActionListeners.add(listener);
    }

    public void removeShapeIsInsertedActionListener(ShapeIsInsertedActionListener listener)
    {
        shapeIsInsertedActionListeners.remove(listener);
    }

    private void fireShapeIsInserted(Shape shape)
    {
        for(ShapeIsInsertedActionListener listener: shapeIsInsertedActionListeners)
        {
            ShapeIsInsertedActionEvent event = new ShapeIsInsertedActionEvent(listener);
            event.setShape(shape);
            listener.shapeIsInserted(event);
        }
    }


    private ArrayList<ShapeHasBeenDeletedActionListener> shapeHasBeenDeletedActionListeners = new ArrayList<>();

    public void addShapeHasBeenDeletedActionListener(ShapeHasBeenDeletedActionListener listener)
    {
        shapeHasBeenDeletedActionListeners.add(listener);
    }

    public void removeShapeHasBeenDeletedActionListener(ShapeHasBeenDeletedActionListener listener)
    {
        shapeHasBeenDeletedActionListeners.remove(listener);
    }

    private void fireShapeHasBeenDeleted(Shape shape)
    {
        for(ShapeHasBeenDeletedActionListener listener: shapeHasBeenDeletedActionListeners)
        {
            ShapeHasBeenDeletedActionEvent event = new ShapeHasBeenDeletedActionEvent(listener);
            event.setShape(shape);
            listener.shapeHasBeenDeleted(event);
        }
    }

}
