package gui;

import events.ChangedColorActionEvent;
import events.ChangedColorActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ColorButton extends JButton implements ChangedColorActionListener
{
    private Color currentColor;

    public ColorButton(Color color)
    {
        super();
        setPreferredSize(new Dimension(44, 44));
        setMaximumSize(new Dimension(44, 44));
        setBackground(color);
        setForeground(color);
        setSelectedColor(color);
        setToolTipText("Click to change drawing color");
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);

        addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", currentColor);
            setSelectedColor(newColor);
        });
    }

    public Color getSelectedColor() {
        return currentColor;
    }

    public void setSelectedColor(Color newColor) {
        setSelectedColor(newColor, true);
    }

    public void setSelectedColor(Color newColor, boolean notify)
    {
        if (newColor == null) return;

        currentColor = newColor;
        setBackground(newColor);
        setForeground(newColor);

        repaint();

        if (notify)
        {
            fireChangedColor();
        }
    }

    @Override
    public void changedColor(ChangedColorActionEvent event) {
        this.setSelectedColor(event.getColor(), false);
    }


    // ------------------------------- EVENTS ---------------------------------

    private final ArrayList<ChangedColorActionListener> listeners = new ArrayList<>();

    public void addChangedColorListener(ChangedColorActionListener listener)
    {
        listeners.add(listener);
    }

    public void removeRepaintActionListener(ChangedColorActionListener listener) {
        listeners.remove(listener);
    }

    private void fireChangedColor()
    {
        for(ChangedColorActionListener listener: listeners)
        {
            ChangedColorActionEvent event = new ChangedColorActionEvent(listener);
            event.setColor(this.getSelectedColor());
            listener.changedColor(event);
        }
    }
}
