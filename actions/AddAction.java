package actions;

import shapes.Drawing;
import shapes.Shape;

/**
 * AddAction implements a single undoable action where a Shape is added to a
 * Drawing.
 */
public class AddAction extends ShapeAction implements DrawAction, UnityAction
{
	Drawing d;

	/**
	 * Creates an AddAction that adds the given Shape to the given Drawing.
	 * 
	 * @param dr
	 *            the drawing into which the shape should be added.
	 * @param sh
	 *            the shape to be added.
	 */
	public AddAction(Drawing dr, Shape sh)
	{
		super(sh);
		this.d = dr;
	}

	public void execute() {
		d.insertShape(shape);
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		d.removeShape(shape);
	}

	@Override
	public boolean unity(UnityAction otherObject)
	{
		if (!(otherObject instanceof ChangeSizeAction)) {
			return false;
		}

		ChangeSizeAction otherCommand = (ChangeSizeAction) otherObject;

		if (!this.shape.equals(otherCommand.shape)) {
			return false;
		}

		this.shape.setPoint2(otherCommand.newPoint2);
		return true;
	}

	@Override
	public void setNonUnity() {}

	@Override
	public boolean canUnity()
	{
		return true;
	}
}
