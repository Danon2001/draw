package gui.graphicShapes;

import shapes.Rectangle;

import java.awt.*;

public class GraphicRectangle extends GraphicFillableShape {

    public GraphicRectangle(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public void drawFilled(Graphics g) {
        g.fillRect(shape.getPosition().x, shape.getPosition().y, shape.getSize().x, shape.getSize().y);
    }

    @Override
    public void drawNonFilled(Graphics g) {
        g.drawRect(shape.getPosition().x, shape.getPosition().y, shape.getSize().x, shape.getSize().y);
    }
}
