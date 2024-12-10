package shapes;

import java.awt.Point;

public class Line extends Shape
{

	public Line(int x, int y) {
		super(new Point(x, y));
	}

	public Line(Line line) {
		this(line.point1.x, line.point1.y);
	}

	@Override
	public String toString() {
		return "line;" + super.toString();
	}

	@Override
	public Shape clone()
	{
		return new Line(this);
	}
}
