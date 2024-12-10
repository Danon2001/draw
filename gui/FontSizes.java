package gui;

import javax.swing.*;
import java.awt.*;

public class FontSizes extends JSpinner
{
    private static final Dimension DEFAULT_PREFERRED_SIZE = new Dimension(0, 20);

    private static final Dimension DEFAULT_MAXIMUM_SIZE = new Dimension(0, 20);

    private static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension(0, 20);

    private static final int DEFAULT_VALUE = 12;

    private static final int DEFAULT_MIN = 6;

    private static final int DEFAULT_MAX = 96;

    private static final int DEFAULT_STEP = 1;

    FontSizes()
    {
        super(new SpinnerNumberModel(DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_STEP));
        setPreferredSize(DEFAULT_PREFERRED_SIZE);
        setMaximumSize(DEFAULT_MAXIMUM_SIZE);
        setMinimumSize(DEFAULT_MINIMUM_SIZE);
    }
}
