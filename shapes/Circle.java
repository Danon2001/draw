package shapes;

import java.awt.*;


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

	public static String getName()
	{
		return "circ";
	}

	public static Circle fromString(String string)
	{
		String[] parts = string.split(";");
		parts[0] = parts[0].trim();
		Point point1 = getPointFromString(parts[1]);
		Point point2 = getPointFromString(parts[2]);
		boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
				: true;
		Circle circle = new Circle(point1.x, point1.y, fill);
		circle.setPoint2(point2);
		circle.setColor(new Color(Integer.parseInt(parts[3]
				.trim())));
		return circle;
	}

	@Override
	public Shape clone()
	{
		return new Circle(this);
	}
}
