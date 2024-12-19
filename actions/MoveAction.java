package actions;

import java.awt.Point;

import shapes.Selection;

/**
 * MoveAction implements a single undoable action where all the Shapes in a
 * given Selection are moved.
 */
public class MoveAction extends SelectionAction implements DrawAction, UnityAction
{
	Point movement;
	boolean isUnity;

	/**
	 * Creates a MoveAction that moves all Shapes in the given Selection in the
	 * direction given by the point. The movement is relative to the shapes
	 * original position.
	 * 
	 * @param s
	 *            a selection which contains the shapes to be moved
	 * @param m
	 *            the amount the shapes should be moved, relative to the
	 *            original position
	 */
	public MoveAction(Selection s, Point m)
	{
		super(s);
		this.movement = m;
		this.isUnity = true;
	}

	public void execute() {
		select.move(movement.x, movement.y);
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		select.move(-movement.x, -movement.y);
	}

	public boolean unity(UnityAction otherObject)
	{
		if (otherObject instanceof MoveAction otherCommand && otherCommand.isUnity && this.isUnity)
		{
			if (this.select.equals(otherCommand.select))
			{
				this.movement.translate(otherCommand.movement.x, otherCommand.movement.y);
				return true;
			}
		}
		if (otherObject instanceof CompleteMoveAction)
		{
			this.setNonUnity();
			return true;
		}
		return false;
	}

	@Override
	public void setNonUnity()
	{
		isUnity= false;
	}

	@Override
	public boolean canUnity()
	{
		return this.isUnity;
	}
}
