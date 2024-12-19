package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import controller.DrawIO;
import controller.DrawingController;
import events.*;

import javax.swing.*;

/**
 * 
 * Represents a main menu for a DrawGUI
 * 
 * @author Alex Lagerstedt
 * 
 */
public class MainMenu extends JMenuBar implements EnableClearActionListener,
		EnableDeleteActionListener, EnableRedoActionListener,
		EnableUndoActionListener, EnableSelectAllActionListener, ActionListener
{

		private static final long serialVersionUID = 0;
		private JMenuItem undo;

		private JMenuItem redo;

		private JMenuItem clear;

		private JMenuItem delete;

		private JMenuItem all;

		DrawingController controller;

		DrawGUI.DrawingContainer drawingContainer;

		private HashMap<String, Runnable> actions = new HashMap<>();

		private DrawIO dio;


		public MainMenu(DrawingController c, DrawGUI.DrawingContainer drawingContainer)
		{
			this.controller = c;
			this.drawingContainer = drawingContainer;
			dio = new DrawIO();
			this.makeActions();

			JMenu fileMenu = new JMenu("File");
			JMenuItem newdrawing = new JMenuItem("New", new ImageIcon(
					"img/document-new.png"));
			JMenuItem open = new JMenuItem("Open", new ImageIcon(
					"img/document-open.png"));
			JMenuItem saveas = new JMenuItem("Save as", new ImageIcon(
					"img/document-save-as.png"));
			JMenuItem export = new JMenuItem("Export PNG", new ImageIcon(
					"img/document-save-as.png"));
			JMenuItem quit = new JMenuItem("Quit", new ImageIcon(
					"img/system-log-out.png"));

			JMenu editMenu = new JMenu("Edit");
			undo = new JMenuItem("Undo", new ImageIcon("img/edit-undo.png"));
			undo.setEnabled(false);
			redo = new JMenuItem("Redo", new ImageIcon("img/edit-redo.png"));
			redo.setEnabled(false);

			JMenu selectionMenu = new JMenu("Selection");
			all = new JMenuItem("Select all", new ImageIcon(
					"img/edit-select-all.png"));
			all.setEnabled(false);
			clear = new JMenuItem("Clear selection", new ImageIcon(
					"img/edit-clear.png"));
			clear.setEnabled(false);
			delete = new JMenuItem("Delete", new ImageIcon(
					"img/edit-delete.png"));
			delete.setEnabled(false);
			delete.setActionCommand("Delete");

			final JMenu helpMenu = new JMenu("Help");
			final JMenuItem about = new JMenuItem("About", new ImageIcon(
					"img/dialog-information.png"));

			redo.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));
			open.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
			newdrawing.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
			undo.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));
			quit.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
			export.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
			saveas.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
			clear.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
			all.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
					java.awt.Event.CTRL_MASK));
			delete.setAccelerator(KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_DELETE, 0));

			quit.addActionListener(this);
			all.addActionListener(this);
			undo.addActionListener(this);
			redo.addActionListener(this);
			about.addActionListener(this);
			delete.addActionListener(this);
			clear.addActionListener(this);
			newdrawing.addActionListener(this);
			open.addActionListener(this);
			saveas.addActionListener(this);
			export.addActionListener(this);

			fileMenu.add(newdrawing);
			fileMenu.add(open);
			fileMenu.addSeparator();
			fileMenu.add(saveas);
			fileMenu.add(export);
			fileMenu.addSeparator();
			fileMenu.add(quit);

			editMenu.add(undo);
			editMenu.add(redo);

			selectionMenu.add(all);
			selectionMenu.add(clear);
			selectionMenu.add(delete);

			helpMenu.add(about);

			this.add(fileMenu);
			this.add(editMenu);
			this.add(selectionMenu);
			this.add(helpMenu);
		}


		@Override
		public void clearEnabled(EnableClearActionEvent event) {
			clear.setEnabled(event.isEnable());
		}

		@Override
		public void deleteEnabled(EnableDeleteActionEvent event) {
			delete.setEnabled(event.isEnable());
		}

		@Override
		public void redoEnabled(EnableRedoActionEvent event) {
			redo.setEnabled(event.isEnable());
		}

		@Override
		public void undoEnabled(EnableUndoActionEvent event) {
			undo.setEnabled(event.isEnable());
		}

		@Override
		public void selectAllEnabled(EnableSelectAllActionEvent event) {
			all.setEnabled(event.isEnable());
		}


		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();
			if (!actions.containsKey(cmd)) {
				JOptionPane.showMessageDialog(null, "Not implemented.",
						"Not implemented", JOptionPane.ERROR_MESSAGE);
				return;
			}
			actions.get(cmd).run();
		}

		private void makeActions()
		{
			actions.put("Quit", () -> System.exit(0));
			actions.put("Undo", () -> controller.undo());
			actions.put("Redo", () -> controller.redo());
			actions.put("Select all", () -> controller.selectAll());
			actions.put("Clear selection", () -> controller.clearSelection());
			actions.put("Delete", () -> controller.deleteSelectedShapes());
			actions.put("Open", () -> (new OpenFileHandler(dio, controller)).showHandlerMessage());
			actions.put("Save as", () -> (new SaveAsHandler(dio, controller)).showHandlerMessage());
			actions.put("Export PNG", () -> (new ExportPNGHandler(dio, controller, drawingContainer.getDrawingPan())).showHandlerMessage());
			actions.put("New", () -> (new NewDrawingHandler(dio, controller)).showHandlerMessage());
		}
}
