package shapes;


public class Circle extends FillableShape
{
	public Circle(int x, int y, boolean filled)
	{
		super(x, y);
		this.filled = filled;
	}

	public Circle(Circle circle) {
		this(circle.point1.x, circle.point1.y, circle.filled);
	}

	public String toString() {
		return "circ;" + super.toString();
	}

	@Override
	public Shape clone()
	{
		return new Circle(this);
	}

}
