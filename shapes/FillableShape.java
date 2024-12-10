package shapes;

import events.FillingChangedActionEvent;
import events.FillingChangedActionListener;

import java.awt.Point;
import java.util.ArrayList;

public abstract class FillableShape extends Shape {

	protected boolean filled;

	public FillableShape(int xpos, int ypos)
	{
		super(new Point(xpos, ypos));
		filled = false;
	}

	@Override
	public void setSelected(boolean b)
	{
		if(!b)
		{
			this.clearFillingChangedActionListener();
		}
		super.setSelected(b);
	}

	public boolean getFilled() {
		return filled;
	}

	public void setFilled(boolean f)
	{
		filled = f;
		this.fireFillingChanged(f);
		this.fireConditionHasChanged();
	}

	public String toString() {
		return super.toString() + ";" + (filled ? 1 : 0);
	}



	// ------------------------------- EVENTS ---------------------------------

	private ArrayList<FillingChangedActionListener> fillingChangedActionListeners = new ArrayList<>();

	public void addFillingChangedActionListener(FillingChangedActionListener listener)
	{
		fillingChangedActionListeners.add(listener);
	}

	public void removeFillingChangedActionListener(FillingChangedActionListener listener)
	{
		fillingChangedActionListeners.remove(listener);
	}

	public void clearFillingChangedActionListener()
	{
		fillingChangedActionListeners.clear();
	}

	private void fireFillingChanged(boolean fill)
	{
		for(FillingChangedActionListener listener: fillingChangedActionListeners)
		{
			FillingChangedActionEvent event = new FillingChangedActionEvent(listener);
			event.setFill(fill);
			listener.fillingChanged(event);
		}
	}

}
