package shapes;

import java.awt.*;


public class Text extends Shape
{
	private String text;
	private Font font;


	public Text(Text text)
	{
		this(text.point1.x, text.point1.y, text.font.getSize(), text.text);
	}


	/**
	 * Constructs a new Text shape with the given string as the text.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param fontSize
	 *            font size
	 * @param str
	 *            the text for the shape
	 */
	public Text(int x, int y, int fontSize, String str)
	{
		super(new Point(x, y));
		font = new Font(null, Font.PLAIN, fontSize);
		text = str;
	}

	public Font getFont() {
		return font;
	}

	public String toString()
	{
		return "text;" + super.toString() + ";" + font.getSize() + ";"
				+ text.replace(';', '?');
	}

	public String getText()
	{
		return text;
	}

	public static String getName()
	{
		return "text";
	}

	public static Text fromString(String string)
	{
		String[] parts = string.split(";");
		parts[0] = parts[0].trim();
		Point point1 = getPointFromString(parts[1]);
		Point point2 = getPointFromString(parts[2]);
		int fontSize = Integer.parseInt(parts[4].trim());
		Text text = new Text(point1.x, point1.y, fontSize, parts[5]);
		text.setPoint2(point2);
		text.setColor(new Color(Integer.parseInt(parts[3]
				.trim())));
		return text;
	}

	@Override
	public Shape clone()
	{
		return new Text(this);
	}
}
