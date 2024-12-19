package gui;

import events.*;
import shapes.Drawing;
import shapes.Shape;
import tools.ShapeTool;
import tools.ShapeToolFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class DrawingPan extends JPanel implements ConditionHasChangedActionListener,
        ShapeHasBeenDeletedActionListener, ShapeIsInsertedActionListener
{
    private static final long serialVersionUID = 0;

    private ArrayList<ShapeTool> graphicShapes;

    private ShapeToolFactory graphicShapeFactory;

    public DrawingPan(Drawing drawing, ToolBox toolBox)
    {
        graphicShapes = new ArrayList<ShapeTool>(0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);
                toolBox.getSelectedTool().mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                toolBox.getSelectedTool().mouseReleased(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                toolBox.getSelectedTool().mouseMoved(e);
            }
            @Override
            public void mouseDragged(MouseEvent e)
            {
                super.mouseDragged(e);
                toolBox.getSelectedTool().mouseDragged(e);
            }
        });

        graphicShapeFactory = new ShapeToolFactory();

        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        setPreferredSize(drawing.getSize());
    }

    public void addShape(Shape shape) {
        graphicShapes.add(graphicShapeFactory.create(shape));
    }

    public void removeShape(Shape shape)
    {
        graphicShapes.remove(graphicShapeFactory.getGraphicShape(shape));
        graphicShapeFactory.removeGraphicShape(shape);
    }

    public BufferedImage getImage()
    {
        BufferedImage bi = new BufferedImage(getPreferredSize().width,
                getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        this.print(g);
        return bi;
    }

    public void drawShapes(Graphics g)
    {
        for (ShapeTool graphicShape : graphicShapes)
        {
            graphicShape.draw(g);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.drawShapes(g);
    }

    @Override
    public void conditionHasChanged(ConditionHasChangedActionEvent event) {
        this.repaint();
    }

    @Override
    public void shapeHasBeenDeleted(ShapeHasBeenDeletedActionEvent event)
    {
        removeShape(event.getShape());
    }

    @Override
    public void shapeIsInserted(ShapeIsInsertedActionEvent event) {
        addShape(event.getShape());
    }
}
