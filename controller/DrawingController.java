package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import actions.*;
import events.*;
import shapes.Shape;
import shapes.Drawing;
import shapes.FillableShape;

import gui.DrawGUI;
import gui.ToolBox;


public class DrawingController implements ChangedColorActionListener
{
    private DrawGUI gui;
    private UndoManager undoManager;
    private Drawing drawing;
    private ToolBox toolBox;


    public DrawingController(DrawGUI g)
    {
        drawing = null;
        undoManager = new UndoManager();
        gui = g;
    }

    public void addShape(Shape s)
    {
        DrawAction add = new AddAction(drawing, s);
        add.execute();
        undoManager.addAction(add);
    }

    public void changeSizeShape(Shape s, Point point2)
    {
        DrawAction add = new ChangeSizeAction(s, point2);
        add.execute();
        undoManager.addAction(add);
    }

    public void colorShape(Shape shape, Color color)
    {
        DrawAction c = new ColorAction(shape, color);
        c.execute();
    }

    public void colorSelectedShapes(Color c)
    {
        for (Shape s : drawing.getSelection())
        {
            DrawAction col = new ColorAction(s, c);
            col.execute();
            undoManager.addAction(col);
        }

    }

    public void deleteSelectedShapes()
    {
        DrawAction del = new DeleteAction(drawing.getSelection());
        del.execute();
        undoManager.addAction(del);
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void moveSelectedShapes(Point movement)
    {
        if (!drawing.getSelection().isEmpty())
        {
            DrawAction move = new MoveAction(drawing.getSelection(), movement);
            move.execute();
            undoManager.addAction(move);
        }
    }

    public void completeMoveSelectedShapes()
    {
        if (!drawing.getSelection().isEmpty())
        {
            DrawAction move = new CompleteMoveAction();
            move.execute();
            undoManager.addAction(move);
        }
    }

    public void newDrawing(Dimension size)
    {
        drawing = new Drawing(size);
        if (gui != null)
        {
            gui.updateDrawing();
        }
    }

    public void redo() {
        if (this.undoManager.canRedo())
        {
            this.undoManager.redo();
        }
    }

    public void selectAll()
    {
        drawing.clearSelection();
        drawing.selectAllShapes();
    }

    public void toggleFilled()
    {
        DrawAction toggle = new FillAction(drawing.getSelection());
        toggle.execute();
        undoManager.addAction(toggle);
    }

    public UndoManager getUndoManager()
    {
        return undoManager;
    }

    public void undo()
    {
        if (this.undoManager.canUndo())
        {
            this.undoManager.undo();
        }
    }

    public void clearSelection()
    {
        this.drawing.clearSelection();
    }

    public void addSelectionShape(Shape shape)
    {
        if(shape == null)
        {
            return;
        }
        if(!this.drawing.getSelection().contains(shape)
                && this.drawing.getSelection().isEmpty())
        {
            shape.addChangedColorListener(toolBox.getColorbutton());
        }

        this.drawing.getSelection().add(shape);

        if(shape instanceof FillableShape)
        {
            for (FillingChangedActionListener listener : fillingChangedActionListeners)
            {
                ((FillableShape) shape).addFillingChangedActionListener(listener);
            }
        }
    }

    public void setToolBox(ToolBox toolBox)
    {
        this.toolBox = toolBox;
    }

    @Override
    public void changedColor(ChangedColorActionEvent event)
    {
        this.colorSelectedShapes(event.getColor());
    }



    /*---------------------------------  Events  --------------------------------------------------------------------*/

    private ArrayList<FillingChangedActionListener> fillingChangedActionListeners = new ArrayList<>();

    public void addFillingChangedActionListener(FillingChangedActionListener listener)
    {
        fillingChangedActionListeners.add(listener);
    }

    public void removeFillingChangedActionListener(FillingChangedActionListener listener)
    {
        fillingChangedActionListeners.remove(listener);
    }

}

