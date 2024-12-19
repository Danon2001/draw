package gui;

import controller.DrawIO;
import controller.DrawingController;
import exceptions.DrawIOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveAsHandler extends FileHandler implements Handler
{
    SaveAsHandler(DrawIO drawIO, DrawingController controller)
    {
        super(drawIO, controller);
    }

    @Override
    public void showHandlerMessage()
    {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);

        this.setSelectedFile(new File("new.draw"));
        FileFilter filter = new FileNameExtensionFilter("Draw files",
                "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);

        int result = this.showSaveDialog(null);

        File f = this.getSelectedFile();
        if (f != null && result == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                drawIO.save(f, controller);
            } catch (DrawIOException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showHandlerMessageWithRequest()
    {
        String message = "Есть несохраненные данные. Вы хотите сохранить?";
        String title = "Сохранить?";

        int response = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION)
        {
            this.showHandlerMessage();
        }
    }
}
