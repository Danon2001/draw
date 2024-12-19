package gui;

import controller.DrawIO;
import controller.DrawingController;
import exceptions.DrawIOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ExportPNGHandler extends FileHandler implements Handler
{
    private DrawingPan drawingPan;

    ExportPNGHandler(DrawIO drawIO, DrawingController controller, DrawingPan drawingPan)
    {
        super(drawIO, controller);
        this.drawingPan = drawingPan;
    }

    @Override
    public void showHandlerMessage()
    {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setDialogType(JFileChooser.CUSTOM_DIALOG);
        FileFilter filter = new FileNameExtensionFilter(
                "Portable Network Graphics", "png");
        this.addChoosableFileFilter(filter);

        this.setSelectedFile(new File("out.png"));
        int result = this.showSaveDialog(null);

        File f = this.getSelectedFile();
        if (f != null && result == JFileChooser.APPROVE_OPTION) {
            try
            {
                drawIO.export(f, controller, drawingPan);
            } catch (DrawIOException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
