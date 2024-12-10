package gui.graphicShapes;

import shapes.Text;

import java.awt.*;

public class GraphicText extends GraphicShape
{
    public GraphicText(Text text) {
        super(text);
    }

    @Override
    public void drawShape(Graphics g)
    {
        g.setFont(((Text)shape).getFont());
        int w = g.getFontMetrics().stringWidth(((Text)shape).getText());
        shape.setPoint2(new Point(shape.getPoint1().x + w, shape.getPoint1().y - ((Text)shape).getFont().getSize()));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(((Text)shape).getText(), shape.getPoint1().x, shape.getPoint1().y);
    }
}
