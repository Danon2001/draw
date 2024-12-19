package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import events.*;
import controller.DrawingController;
import shapes.FillableShape;
import shapes.Text;
import shapes.Shape;
import tools.*;


public class ToolBox extends JToolBar implements SelectedShapeActionListener
{

	private static final long serialVersionUID = 1L;

	private DrawingController c;
	private ButtonGroup buttons;
	private ColorButton colorbutton;
	private SelectFillCheckBox selectFillCheckBox;
	private FontSizes fontSizes;

	private Tool selectedTool;

	public static final ToolEnum[] namesOfTools = new ToolEnum[]{ ToolEnum.SELECT, ToolEnum.LINE, ToolEnum.RECTANGLE, ToolEnum.CIRCLE, ToolEnum.TEXT};

	private HashMap<ToolEnum, Tool> toolsList = new HashMap<>();


	public ToolBox(DrawingController c)
	{
		super("Tools", VERTICAL);
		this.c = c;
		this.c.setToolBox(this);
		makeListOfTools();
		selectedTool = toolsList.get(ToolEnum.SELECT);
		buttons = new ButtonGroup();

		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Tools"));
		add(Box.createRigidArea(new Dimension(10, 10)));

		for(ToolEnum tool : namesOfTools)
		{
			JToggleButton btn = new JToggleButton(toolsList.get(tool).getImageIcon());
			btn.addActionListener(e -> {
				if(tool != ToolEnum.SELECT)
				{
					c.clearSelection();
				}
				setSelectedTool(toolsList.get(tool));
			});
			btn.setToolTipText(toolsList.get(tool).getHintText());
			add(btn);
			buttons.add(btn);
		}

		selectFillCheckBox = new SelectFillCheckBox();
		selectFillCheckBox.addItemListener(e -> {
			c.toggleFilled(e.getStateChange() ==  ItemEvent.SELECTED);
		});
		this.c.addFillingChangedActionListener(selectFillCheckBox);

		colorbutton = new ColorButton(Color.BLACK);
		colorbutton.addChangedColorListener(c);

		fontSizes = new FontSizes();

		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Fill"));
		add(selectFillCheckBox);

		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Color"));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(colorbutton);

		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Font"));
		add(Box.createRigidArea(new Dimension(10, 10)));

		JPanel jp = new JPanel(new BorderLayout());
		jp.add(fontSizes, BorderLayout.NORTH);
		add(jp);

	}

	public void makeListOfTools()
	{
		toolsList.put(ToolEnum.SELECT, new SelectTool(c));
		toolsList.put(ToolEnum.LINE, new LineTool(c));
		toolsList.put(ToolEnum.RECTANGLE, new RectangleTool(c));
		toolsList.put(ToolEnum.CIRCLE, new CircleTool(c));
		toolsList.put(ToolEnum.TEXT, new TextTool(c));
	}


	public Color getColor() {
		return colorbutton.getSelectedColor();
	}

	public boolean getFill() {
		return selectFillCheckBox.isFill();
	}

	public int getFontSize() {
		return (Integer) fontSizes.getValue();
	}

	public ColorButton getColorbutton() {
		return this.colorbutton;
	}

	public void setColor(Color color)
	{
		colorbutton.setSelectedColor(color);
	}

	public void setFill(boolean f)
	{
		selectFillCheckBox.setSelected(f);
	}

	public void setFontSize(int size) {
		fontSizes.setValue(size);
	}

	public Tool getSelectedTool()
	{
		return selectedTool;
	}

	public void setSelectedTool(Tool tool)
	{
		selectFillCheckBox.setEnabled(tool instanceof FillableShapeTool);

		this.selectedTool = tool;
	}


	@Override
	public void selectedShape(SelectedShapeActionEvent event)
	{
		Shape selectedShape = event.getShape();

		colorbutton.setEnabled(true);
		fontSizes.setEnabled(false);
		colorbutton.setSelectedColor(selectedShape.getColor(), false);

		if (selectedShape instanceof FillableShape)
		{
			selectFillCheckBox.setEnabled(true);
			this.setFill(((FillableShape) selectedShape).getFilled());
		} else
		{
			selectFillCheckBox.setEnabled(false);
			this.setFill(false);
		}

		if (selectedShape instanceof Text)
		{
			this.setFontSize(((Text) selectedShape).getFont().getSize());
		}
	}

}
