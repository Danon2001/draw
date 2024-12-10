package gui.graphicShapes;

import shapes.Line;

import java.awt.*;

public class GraphicLine extends GraphicShape
{
    public GraphicLine(Line line) {
        super(line);
    }

    @Override
    public void drawShape(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke((float) strokeWidth));

        g.drawLine(shape.getPoint1().x, shape.getPoint1().y, shape.getPoint2().x, shape.getPoint2().y);
    }
}
