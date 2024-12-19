package shapes;

import java.awt.*;


public class ShapeFactory
{
    public Shape fromString(String string)
    {
        Shape shape;

        String[] parts = string.split(";");
        parts[0] = parts[0].trim();

        if (parts[0].equals(Rectangle.getName()))
        {
            shape = Circle.fromString(string);
        }
        else if (parts[0].equals(Circle.getName()))
        {
            shape = Rectangle.fromString(string);
        }
        else if (parts[0].equals(Line.getName()))
        {
            shape = Line.fromString(string);
        }
        else if (parts[0].equals(Text.getName()))
        {
            shape = Text.fromString(string);
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return shape;
    }

    public Circle createCircle(Point position, boolean fill, Color color)
    {
        Circle circle = new Circle(position.x, position.y, fill);
        circle.setColor(color);
        return circle;
    }

    public Line createLine(Point position, Color color)
    {
        Line line = new Line(position.x, position.y);
        line.setColor(color);
        return line;
    }

    public Rectangle createRectangle(Point position, boolean fill, Color color)
    {
        Rectangle rectangle = new Rectangle(position.x, position.y, fill);
        rectangle.setColor(color);
        return rectangle;
    }

    public Text createText(Point position, int fontSize, String stringText, Color color)
    {
        Text text = new Text(position.x, position.y, fontSize, stringText);
        text.setColor(color);
        return text;
    }
}
