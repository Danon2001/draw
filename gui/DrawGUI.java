package gui;

import java.awt.*;

import javax.swing.*;

import shapes.Drawing;
import controller.DrawingController;

/**
 * Graphical user interface for the Drawing editor "Draw"
 * 
 * @author Alex Lagerstedt
 * 
 */

public class DrawGUI extends JFrame {

	/**
	 * A simple container that contains a Drawing instance and keeps it
	 * centered.
	 * 
	 * @author Alex Lagerstedt
	 * 
	 */
	public class DrawingContainer extends JPanel
	{

		private static final long serialVersionUID = 0;

		private DrawingPan drawingPan;


		public DrawingContainer() {
			super(new GridBagLayout());
		}

		public void setDrawing(Drawing drawing)
		{
			this.removeAll();

			drawingPan = new DrawingPan(drawing, tools);
			this.add(drawingPan);
			drawing.addRepaintActionListener(drawingPan);
			drawing.addShapeIsInsertedActionListener(drawingPan);
			drawing.addShapeHasBeenDeletedActionListener(drawingPan);
			drawing.addShapeHasBeenDeletedActionListener(mainMenu);
			setPreferredSize(drawingPan.getPreferredSize());

			pack();
		}

		public DrawingPan getDrawingPan()
		{
			return drawingPan;
		}

		}


	public class StatusBar extends JLabel
	{
		private static final long serialVersionUID = 0;

		public StatusBar() {
			super();
			super.setPreferredSize(new Dimension(100, 16));
			setMessage("Ready");
		}

		public void setMessage(String message) {
			setText(" " + message);
		}
	}

	private DrawingController controller;
	private DrawingContainer drawingContainer;
	private ToolBox tools;
	private JScrollPane scrollpane;
	private MainMenu mainMenu;


	private static final long serialVersionUID = 0;

	public static void main(String[] args) {

		new DrawGUI();

	}

	/**
	 * Constructs a new graphical user interface for the program and shows it.
	 */
	public DrawGUI() {

		this.setTitle("Draw 0.2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawingContainer = new DrawingContainer();
		scrollpane = new JScrollPane(drawingContainer);

		controller = new DrawingController(this);
		tools = new ToolBox(controller);
		getContentPane().add(tools, BorderLayout.WEST);
		getContentPane().add(scrollpane, BorderLayout.CENTER);

		MenuListener mainMenuListener = new MenuListener(controller, drawingContainer);
		mainMenu = new MainMenu(mainMenuListener);
		controller.getUndoManager().addChangedUndoStackActionListener(mainMenu);
		controller.getUndoManager().addChangedRedoStackActionListener(mainMenu);
		this.setJMenuBar(mainMenu);

		controller.newDrawing(new Dimension(500, 380));
		pack();
		setVisible(true);

	}

	/**
	 * Updates the GUI to show the Drawing instance that is currently controlled
	 * by the DrawingController.
	 */
	public void updateDrawing() {

		drawingContainer.setDrawing(controller.getDrawing());

		controller.getDrawing().getSelection().addClearSelectedShapesActionListener(tools);
		controller.getDrawing().getSelection().addClearSelectedShapesActionListener(mainMenu);

		controller.getDrawing().getSelection().addSelectShapeActionListener(tools);
		controller.getDrawing().getSelection().addSelectShapeActionListener(mainMenu);

		controller.getDrawing().getSelection().addSelectedManyShapesActionListener(tools);
		controller.getDrawing().getSelection().addSelectedManyShapesActionListener(mainMenu);

		scrollpane.setPreferredSize(new Dimension(drawingContainer
				.getPreferredSize().width + 100, drawingContainer
				.getPreferredSize().height + 100));
		pack();
		repaint();
	}
}
