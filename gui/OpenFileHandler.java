package gui;

import controller.DrawIO;
import controller.DrawingController;
import exceptions.DrawIOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class OpenFileHandler extends FileHandler implements Handler
{
    OpenFileHandler(DrawIO drawIO, DrawingController controller) {
        super(drawIO, controller);
    }

    @Override
    public void showHandlerMessage()
    {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filter = new FileNameExtensionFilter("Draw files",
                "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);

        this.showOpenDialog(null);
        File f = this.getSelectedFile();
        if (f != null)
        {
            try
            {
                drawIO.open(f, controller, new SaveAsHandler(drawIO, controller));
            } catch (DrawIOException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
