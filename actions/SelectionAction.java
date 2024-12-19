package actions;

import shapes.Selection;

public abstract class SelectionAction
{
    protected Selection select;

    public SelectionAction(Selection select) {
        this.select = select.clone();
    }
}
