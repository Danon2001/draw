package shapes;

import java.awt.*;

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

	public static String getName()
	{
		return "line";
	}

	@Override
	public Shape clone()
	{
		return new Line(this);
	}

	public static Line fromString(String string)
	{
		String[] parts = string.split(";");
		parts[0] = parts[0].trim();
		Point point1 = getPointFromString(parts[1]);
		Point point2 = getPointFromString(parts[2]);
		Line line = new Line(point1.x, point1.y);
		line.setPoint2(point2);
		line.setColor(new Color(Integer.parseInt(parts[3]
				.trim())));
		return line;
	}
}
