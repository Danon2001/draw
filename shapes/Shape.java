package shapes;

import events.ChangedColorActionEvent;
import events.ChangedColorActionListener;
import events.ConditionHasChangedActionEvent;
import events.ConditionHasChangedActionListener;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Shape
{
	private static final Color DEFAULT_COLOR = Color.BLACK;
	private static final int DEFAULT_WIDTH = 25;
	private static final int DEFAULT_HEIGHT = 25;

	protected Point point1;
	protected Point point2;

	protected Color color;
	protected boolean selected;

	public Shape(Point p)
	{
		point1 = p;
		point2 = new Point(p.x + DEFAULT_WIDTH, p.y + DEFAULT_HEIGHT);
		color = DEFAULT_COLOR;
		selected = false;
	}

	public Color getColor() {
		return color;
	}

	public Point getPosition()
	{
		return new Point(Math.min(point1.x, point2.x), Math.min(point1.y,
				point2.y));
	}

	public Point getSize()
	{
		return new Point(Math.abs(point2.x - point1.x), Math.abs(point2.y
				- point1.y));
	}

	/**
	 * Checks if the Shape contains the given point. Has a 2 pixel margin in all
	 * directions.
	 * 
	 * @param p
	 *            point to check
	 * @return true if shape includes given point
	 */
	public boolean includes(Point p)
	{
		if ((p.x - getPosition().x > -2)
				&& (p.x - getPosition().x < getSize().x + 2)
				&& (p.y - getPosition().y > -2)
				&& (p.y - getPosition().y < getSize().y + 2)) {
			return true;
		}
		return false;
	}

	public void move(int x, int y)
	{
		point1.x = point1.x + x;
		point1.y = point1.y + y;
		point2.x = point2.x + x;
		point2.y = point2.y + y;

		this.fireConditionHasChanged();
	}

	public void setColor(Color c)
	{
		color = c;
		this.fireConditionHasChanged();
		this.fireChangedColor(c);
	}

	public void setPoint1(Point p) {
		this.point1 = p;
	}

	public void setPoint2(Point p)
	{
		this.point2 = p;
		this.fireConditionHasChanged();
	}

	public boolean getSelected()
	{
		return selected;
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	public String toString()
	{
		String str = "" + point1.x;
		str += "," + point1.y;
		str += ";" + point2.x;
		str += "," + point2.y;
		str += ";" + color.getRGB();

		return str;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Shape otherShape)
		{
			return this.point1.equals(otherShape.point1)
					&& this.point2.equals(otherShape.point2)
					&& this.selected == otherShape.selected
					&& this.color.equals(otherShape.color);
		}

		return super.equals(obj);
	}

	public Point getPoint2() {return this.point2;}

	public Point getPoint1() { return this.point1;}

	public abstract Shape clone();


	protected static Point getPointFromString(String string)
	{
		String[] p = string.split(",");
		return new Point(Integer.parseInt(p[0].trim()), Integer.parseInt(p[1]
				.trim()));
	}



	// ------------------------------- EVENTS ---------------------------------

	private ArrayList<ConditionHasChangedActionListener> repaintActionListener = new ArrayList<>();

	public void addRepaintActionListener(ConditionHasChangedActionListener listener)
	{
		repaintActionListener.add(listener);
	}

	public void removeRepaintActionListener(ConditionHasChangedActionListener listener)
	{
		repaintActionListener.remove(listener);
	}

	protected void fireConditionHasChanged()
	{
		for(ConditionHasChangedActionListener listener: repaintActionListener)
		{
			ConditionHasChangedActionEvent event = new ConditionHasChangedActionEvent(listener);
			listener.conditionHasChanged(event);
		}
	}


	private final ArrayList<ChangedColorActionListener> changedColorActionListeners = new ArrayList<>();

	public void addChangedColorListener(ChangedColorActionListener listener) {
		changedColorActionListeners.add(listener);
	}

	public void removeRepaintActionListener(ChangedColorActionListener listener)
	{
		changedColorActionListeners.remove(listener);
	}

	public void clearAllRepaintActionListener()
	{
		changedColorActionListeners.clear();
	}

	private void fireChangedColor(Color color)
	{
		for(ChangedColorActionListener listener: changedColorActionListeners)
		{
			ChangedColorActionEvent event = new ChangedColorActionEvent(listener);
			event.setColor(color);
			listener.changedColor(event);
		}
	}
}
