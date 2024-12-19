package actions;

import shapes.Selection;

/**
 * FillAction implements a undoable action where the fill status of all the
 * Shapes in a given Selection are toggled.
 */
public class FillAction extends SelectionAction implements DrawAction
{
	private boolean fill;


	public FillAction(Selection select, boolean fill)
	{
		super(select);
		this.fill = fill;
	}

	public void execute()
	{
		select.switchingFillShapes(fill);
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		execute();
	}

	public void undo()
	{
		select.switchingFillShapes(!fill);
	}

}
