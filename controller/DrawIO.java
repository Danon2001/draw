package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import exceptions.DrawIOException;

import gui.DrawingPan;
import gui.SaveAsHandler;
import shapes.Shape;
import shapes.Drawing;
import shapes.ShapeFactory;


public class DrawIO
{
    public void export(File f, DrawingController c, DrawingPan drawingPan) throws DrawIOException
    {
        try
        {
            c.getDrawing().clearSelection();
            BufferedImage bi = drawingPan.getImage(); // retrieve image
            ImageIO.write(bi, "png", f);
        }
        catch (IOException e)
        {
            throw new DrawIOException(e.getMessage());
        }
    }

    public void open(File f, DrawingController c, SaveAsHandler handler) throws DrawIOException
    {
        int lineNumber = 1;
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(f));
            String str;

            ShapeFactory shapeFactory = new ShapeFactory();

            c.newDrawing(Drawing.fromString(in.readLine()), handler);

            while ((str = in.readLine()) != null)
            {
                try {
                    lineNumber++;
                    if (str.length() == 0) {
                        continue;
                    }

                    Shape shape = shapeFactory.fromString(str);
                    if (shape != null) {
                        c.addShape(shape);
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new DrawIOException("Could not read line " + lineNumber + " in file \"" + f + "\"");
                }
            }
                in.close();
        }
        catch (IOException e)
        {
            throw new DrawIOException(e.getMessage());
        }
    }

    public void save(File f, DrawingController c) throws DrawIOException
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(f));

            out.write(c.getDrawing().toString());
            out.close();

        }
        catch (IOException e)
        {
            throw new DrawIOException("Could not save the drawing.");
        }
    }
}

