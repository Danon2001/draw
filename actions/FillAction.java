package actions;

import shapes.Selection;

/**
 * FillAction implements a undoable action where the fill status of all the
 * Shapes in a given Selection are toggled.
 */
public class FillAction implements DrawAction {

	Selection selected;

	/**
	 * Creates a FillAction that filps the fill status of all FillableShape
	 * instances in the given selection.
	 * 
	 * @param s
	 *            a selection which contains the shapes to be modified
	 */
	public FillAction(Selection s) {
		this.selected = s.clone();
	}

	public void execute()
	{
		selected.switchingFillShapes();
	}

	public String getDescription() {
		return null;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		execute();
	}

}
