package tools;

import shapes.*;

import java.util.HashMap;
import java.util.Map;

public class ShapeToolFactory
{
    private Map<Shape, ShapeTool> shapes = new HashMap<>();

    public ShapeTool create(Shape shape)
    {
        if(shapes.containsKey(shape))
        {
            return shapes.get(shape);
        }

        if (shape instanceof Circle)
        {
            return create((Circle) shape);

        } else if (shape instanceof Line)
        {
            return create((Line) shape);

        } else if (shape instanceof Rectangle)
        {
            return create((Rectangle) shape);

        } else if (shape instanceof Text)
        {
            return create((Text) shape);

        } else
        {
            return null;
        }
    }

    public CircleTool create(Circle circle)
    {
        if(shapes.containsKey(circle))
        {
            return (CircleTool) shapes.get(circle);
        }
        CircleTool graphicCircle = new CircleTool(circle);
        shapes.put(circle, graphicCircle);
        return graphicCircle;
    }

    public LineTool create(Line line)
    {
        if(shapes.containsKey(line))
        {
            return (LineTool) shapes.get(line);
        }
        LineTool graphicLine = new LineTool(line);
        shapes.put(line, graphicLine);
        return graphicLine;
    }

    public RectangleTool create(Rectangle rectangle)
    {
        if(shapes.containsKey(rectangle))
        {
            return (RectangleTool) shapes.get(rectangle);
        }
        RectangleTool graphicRectangle = new RectangleTool(rectangle);
        shapes.put(rectangle, graphicRectangle);
        return graphicRectangle;
    }

    public TextTool create(Text text)
    {
        if(shapes.containsKey(text))
        {
            return (TextTool) shapes.get(text);
        }
        TextTool graphicText = new TextTool(text);
        shapes.put(text, graphicText);
        return graphicText;
    }

    public ShapeTool getGraphicShape(Shape shape) {
        return shapes.get(shape);
    }

    public void removeGraphicShape(Shape shape) {
        shapes.remove(shape);
    }

}
