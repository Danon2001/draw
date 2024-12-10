package gui.graphicShapes;

import shapes.*;

import java.util.HashMap;
import java.util.Map;

public class GraphicShapesFactory
{
    private Map<Shape, GraphicShape> shapes = new HashMap<>();

    public GraphicShape create(Shape shape)
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

    public GraphicCircle create(Circle circle)
    {
        if(shapes.containsKey(circle))
        {
            return (GraphicCircle) shapes.get(circle);
        }
        GraphicCircle graphicCircle = new GraphicCircle(circle);
        shapes.put(circle, graphicCircle);
        return graphicCircle;
    }

    public GraphicLine create(Line line)
    {
        if(shapes.containsKey(line))
        {
            return (GraphicLine) shapes.get(line);
        }
        GraphicLine graphicLine = new GraphicLine(line);
        shapes.put(line, graphicLine);
        return graphicLine;
    }

    public GraphicRectangle create(Rectangle rectangle)
    {
        if(shapes.containsKey(rectangle))
        {
            return (GraphicRectangle) shapes.get(rectangle);
        }
        GraphicRectangle graphicRectangle = new GraphicRectangle(rectangle);
        shapes.put(rectangle, graphicRectangle);
        return graphicRectangle;
    }

    public GraphicText create(Text text)
    {
        if(shapes.containsKey(text))
        {
            return (GraphicText) shapes.get(text);
        }
        GraphicText graphicText = new GraphicText(text);
        shapes.put(text, graphicText);
        return graphicText;
    }

    public GraphicShape getGraphicShape(Shape shape) {
        return shapes.get(shape);
    }

    public void removeGraphicShape(Shape shape) {
        shapes.remove(shape);
    }
}
