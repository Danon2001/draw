package gui;

import events.FillingChangedActionEvent;
import events.FillingChangedActionListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SelectFillCheckBox extends JCheckBox implements ItemListener, FillingChangedActionListener
{
    private boolean fill;

    SelectFillCheckBox() {
        addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.DESELECTED)
        {
            setFill(false);
        }
        else
        {
            setFill(true);
        }
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void fillingChanged(FillingChangedActionEvent event) {
        this.setSelected(event.getFill());
    }
}
