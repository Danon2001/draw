package shapes;

import java.awt.*;


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

	public static String getName()
	{
		return "rect";
	}

	@Override
	public Shape clone()
	{
		return new Rectangle(this);
	}

	public static Rectangle fromString(String string)
	{
		String[] parts = string.split(";");
		parts[0] = parts[0].trim();
		Point point1 = getPointFromString(parts[1]);
		Point point2 = getPointFromString(parts[2]);
		boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
				: true;
		Rectangle rectangle = new Rectangle(point1.x, point1.y, fill);
		rectangle.setPoint2(point2);
		rectangle.setColor(new Color(Integer.parseInt(parts[3]
				.trim())));

		return rectangle;
	}
}
