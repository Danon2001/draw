package gui;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import shapes.Circle;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Text;
import events.SelectedShapeActionEvent;
import events.SelectedShapeActionListener;
import controller.DrawingController;


/**
 * MouseListener listens to the mouse events in a drawing and modifies the
 * Drawing through a DrawingController
 * 
 * @author Alex Lagerstedt
 * 
 */

public class MouseListener extends MouseAdapter {

	private DrawingController c;
	private ToolBox tools;

	boolean isDrawing;

	private Point lastPos;

	private Shape newShape;

	/**
	 * Constructs a new MouseListener
	 * 
	 * @param c
	 *            the DrawingController through which the modifications will be
	 *            done
	 * @param t
	 *            the ToolBox
	 */
	public MouseListener(DrawingController c, ToolBox t) {
		this.tools = t;
		this.c = c;
		this.newShape = null;

	}

	public void mouseDragged(MouseEvent m) {

		Point positionPoint = m.getPoint();

		if (isDrawing && (newShape != null)) {
			c.changeSizeShape(newShape, lastPos);
		}

		if (tools.getTool() == Tool.SELECT) {
			c.moveSelectedShapes(new Point(positionPoint.x - lastPos.x, positionPoint.y - lastPos.y));
		}

		lastPos = m.getPoint();

	}

	public void mouseMoved(MouseEvent m) {
		lastPos = m.getPoint();
	}

	public void mousePressed(MouseEvent m) {

		Point positionPoint = m.getPoint();

		Tool t = tools.getTool();
		isDrawing = true;

		if (t == Tool.SELECT) {

			Shape tmp = c.getDrawing().getShapeAt(positionPoint);

			if (((m.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == 0)
					&& !c.getDrawing().getSelection().contains(tmp)) {
				c.clearSelection();
			}

			if ((tmp != null) && (!c.getDrawing().getSelection().contains(tmp))) {

				// empty the selection before selecting a new shape if shift is
				// not down

				if (c.getDrawing().getSelection().isEmpty()) {
					this.fireSelectedShape(tmp);
				}
				c.addSelectionShape(tmp);

			}

		}
		else if (t == Tool.RECTANGLE) {
			newShape = new Rectangle(positionPoint.x, positionPoint.y, tools.getFill());
		}
		else if (t == Tool.CIRCLE) {
			newShape = new Circle(positionPoint.x, positionPoint.y, tools.getFill());
		}
		else if (t == Tool.LINE) {
			newShape = new Line(positionPoint.x, positionPoint.y);
		}
		else if (t == Tool.TEXT) {
			try {
				newShape = new Text(positionPoint.x, positionPoint.y, tools.getFontSize());
			}
			catch (IllegalArgumentException e) {
			}
		}

		if (newShape != null) {
			c.colorShape(newShape, tools.getColor());
			c.addShape(newShape);
		}
		if(newShape instanceof Text)
		{
			newShape = null;
		}

	}

	public void mouseReleased(MouseEvent m) {
		isDrawing = false;
		newShape = null;

	}



	/*---------------------------------  Events  --------------------------------------------------------------------*/

	private ArrayList<SelectedShapeActionListener> selectShapeListListener = new ArrayList<>();

	public void addSelectShapeActionListener(SelectedShapeActionListener listener)
	{
		selectShapeListListener.add(listener);
	}

	public void removeSelectShapeActionListener(SelectedShapeActionListener listener)
	{
		selectShapeListListener.remove(listener);
	}

	private void fireSelectedShape(Shape shape)
	{
		for(SelectedShapeActionListener listener: selectShapeListListener)
		{
			SelectedShapeActionEvent event = new SelectedShapeActionEvent(listener);
			event.setShape(shape);
			listener.selectedShape(event);
		}
	}

}
