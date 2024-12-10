package shapes;


public class Rectangle extends FillableShape
{

	public Rectangle(int x, int y, boolean filled)
	{
		super(x, y);
		this.filled = filled;
	}

	public Rectangle(Rectangle rectangle) {
		this(rectangle.point1.x, rectangle.point1.y, rectangle.filled);
	}

	public String toString() {
		return "rect;" + super.toString();
	}

	@Override
	public Shape clone()
	{
		return new Rectangle(this);
	}
}
