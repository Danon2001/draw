package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import shapes.Shape;
import shapes.Drawing;
import actions.AddAction;
import actions.ColorAction;
import actions.DeleteAction;
import actions.DrawAction;
import actions.FillAction;
import actions.MoveAction;
import actions.UndoManager;
import actions.ChangeSizeAction;

import events.RepaintActionEvent;
import events.RepaintActionListener;
import gui.DrawGUI;


public class DrawingController {

    private DrawGUI gui;
    private UndoManager undoManager;
    private Drawing drawing;


    public DrawingController(DrawGUI g) {
        drawing = null;
        undoManager = new UndoManager();
        gui = g;
    }

    public void addShape(Shape s) {
        DrawAction add = new AddAction(drawing, s);
        add.execute();
        undoManager.addAction(add);
        this.fireRepaint();

    }

    public void changeSizeShape(Shape s, Point point2)
    {
        DrawAction add = new ChangeSizeAction(s, point2);
        add.execute();
        undoManager.addAction(add);
        this.fireRepaint();
    }

    public void colorShape(Shape shape, Color color)
    {
        DrawAction c = new ColorAction(shape, color);
        c.execute();
    }

    public void colorSelectedShapes(Color c) {
        for (Shape s : drawing.getSelection()) {
            DrawAction col = new ColorAction(s, c);
            col.execute();
            undoManager.addAction(col);
        }

        this.fireRepaint();
    }

    public void deleteSelectedShapes() {
        DrawAction del = new DeleteAction(drawing.getSelection());
        del.execute();
        undoManager.addAction(del);
        this.fireRepaint();
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void moveSelectedShapes(Point movement) {
        if (!drawing.getSelection().isEmpty()) {
            DrawAction move = new MoveAction(drawing.getSelection(), movement);
            move.execute();
            undoManager.addAction(move);
            this.fireRepaint();
        }
    }

    public void newDrawing(Dimension size) {
        drawing = new Drawing(size);
        if (gui != null) {
            gui.updateDrawing();
        }
    }

    public void redo() {
        if (this.undoManager.canRedo()) {
            this.undoManager.redo();
        }
        this.fireRepaint();
    }

    public void selectAll() {
        drawing.clearSelection();
        drawing.selectAllShapes();
        this.fireRepaint();
    }

    public void toggleFilled() {
        DrawAction toggle = new FillAction(drawing.getSelection());
        toggle.execute();
        undoManager.addAction(toggle);
        this.fireRepaint();
    }

    public void undo() {
        if (this.undoManager.canUndo()) {
            this.undoManager.undo();
        }
        this.fireRepaint();
    }

    public void clearSelection()
    {
        this.drawing.clearSelection();
        this.fireRepaint();
    }

    public void addSelectionShape(Shape shape)
    {
        this.drawing.getSelection().add(shape);
        this.fireRepaint();
    }



    /*---------------------------------  Events  --------------------------------------------------------------------*/

    private ArrayList<RepaintActionListener> repaintActionListener = new ArrayList<>();

    public void addRepaintActionListener(RepaintActionListener listener)
    {
        repaintActionListener.add(listener);
    }

    public void removeRepaintActionListener(RepaintActionListener listener)
    {
        repaintActionListener.remove(listener);
    }

    private void fireRepaint()
    {
        for(RepaintActionListener listener: repaintActionListener)
        {
            RepaintActionEvent event = new RepaintActionEvent(listener);
            listener.repaint(event);
        }
    }

}

