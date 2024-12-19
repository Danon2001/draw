package gui;

import controller.DrawIO;
import controller.DrawingController;

import javax.swing.*;
import java.awt.*;

public class NewDrawingHandler extends JDialog implements Handler
{
    protected DrawIO drawIO;

    protected DrawingController controller;

    NewDrawingHandler(DrawIO drawIO, DrawingController controller)
    {
        this.drawIO = drawIO;
        this.controller = controller;
    }

    @Override
    public void showHandlerMessage()
    {
        this.setLayout(new BoxLayout(this.getContentPane(),
                BoxLayout.Y_AXIS));
        this.setTitle("Drawing size");
        JPanel jp;

        jp = new JPanel();
        jp.add(new JLabel("Width"));
        JSpinner widthSpinner = new JSpinner(
                new SpinnerNumberModel(600, 10, 4096, 1));
        jp.add(widthSpinner);
        this.getContentPane().add(jp);

        jp = new JPanel();
        jp.add(new JLabel("Height"));
        JSpinner heightSpinner = new JSpinner(
                new SpinnerNumberModel(450, 10, 4096, 1));
        jp.add(heightSpinner);
        this.getContentPane().add(jp);

        jp = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            Dimension size = new Dimension((Integer) widthSpinner.getValue(),
                    (Integer) heightSpinner.getValue());
            controller.newDrawing(size, new SaveAsHandler(drawIO, controller));
            dispose();
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            dispose();
        });
        jp.add(ok);
        jp.add(cancel);
        this.getContentPane().add(jp);

        this.setModal(true);
        this.pack();
        setVisible(true);
    }
}
