package gui;

import controller.DrawIO;
import controller.DrawingController;

import javax.swing.*;

public abstract class FileHandler extends JFileChooser
{
    protected DrawIO drawIO;

    protected DrawingController controller;

    FileHandler(DrawIO drawIO, DrawingController controller)
    {
        this.drawIO = drawIO;
        this.controller = controller;
    }
}
