package actions;

public class CompleteMoveAction implements DrawAction, UnityAction
{
    @Override
    public void execute() {}

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void redo() {}

    @Override
    public void undo() {}

    @Override
    public boolean unity(UnityAction otherObject)
    {
        if (otherObject instanceof MoveAction otherCommand)
        {
            otherCommand.setNonUnity();
            return true;
        }
        return false;
    }

    @Override
    public void setNonUnity() {}

    @Override
    public boolean canUnity() {
        return true;
    }
}
