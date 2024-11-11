package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import events.RepaintActionEvent;
import events.RepaintActionListener;
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
	public class DrawingContainer extends JPanel implements RepaintActionListener {

		private static final long serialVersionUID = 0;

		private Drawing drawing;

		public DrawingContainer() {
			super(new GridBagLayout());
		}

		public void setDrawing(Drawing newDrawing) {
			this.removeAll();
			removeAllMouseListeners();
			removeAllMouseMotionListeners();

			// Установка нового рисунка и настройка слушателей
			this.drawing = newDrawing;
			mouse = new MouseListener(controller, tools);
			addMouseListeners(mouse);

			// Настройка внешнего вида панели
			setupPanelAppearance(newDrawing);

			pack();
		}

		private void removeAllMouseListeners()
		{
			for (java.awt.event.MouseListener listener : this.getMouseListeners())
			{
				this.removeMouseListener(listener);
			}
		}

		private void removeAllMouseMotionListeners()
		{
			for (java.awt.event.MouseMotionListener listener : this.getMouseMotionListeners())
			{
				this.removeMouseMotionListener(listener);
			}
		}

		private void addMouseListeners(MouseListener mouseListener)
		{
			this.addMouseListener(mouseListener);
			this.addMouseMotionListener(mouseListener);
			mouseListener.addSelectShapeActionListener(tools);
		}

		private void setupPanelAppearance(Drawing drawing)
		{
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setBackground(Color.WHITE);
			setPreferredSize(drawing.getSize());
		}


		public BufferedImage getImage() {

			BufferedImage bi = new BufferedImage(getPreferredSize().width,
					getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			this.print(g);
			return bi;
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			drawing.drawShapes(g);
		}

		@Override
		public void repaint(RepaintActionEvent event) {
			this.repaint();
		}
	}


	public class StatusBar extends JLabel {

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
	private MouseListener mouse;
	private ToolBox tools;
	private JScrollPane scrollpane;


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
		controller.addRepaintActionListener(drawingContainer);
		tools = new ToolBox(controller);
		controller.newDrawing(new Dimension(500, 380));


		getContentPane().add(tools, BorderLayout.WEST);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		// getContentPane().add(statusBar, BorderLayout.SOUTH);

		MenuListener mainMenuListener = new MenuListener(controller, drawingContainer);
		JMenuBar mainMenu = new MainMenu(mainMenuListener);
		this.setJMenuBar(mainMenu);

		pack();
		setVisible(true);

	}

	/**
	 * Updates the GUI to show the Drawing instance that is currently controlled
	 * by the DrawingController.
	 */
	public void updateDrawing() {

		drawingContainer.setDrawing(controller.getDrawing());
		scrollpane.setPreferredSize(new Dimension(drawingContainer
				.getPreferredSize().width + 100, drawingContainer
				.getPreferredSize().height + 100));
		pack();
		repaint();
	}
}
