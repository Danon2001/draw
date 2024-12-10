package actions;

public interface UnityAction
{
    public boolean unity(UnityAction otherObject);

    public void setNonUnity();

    public boolean canUnity();
}
