package shapes;

import java.util.ArrayList;
import java.util.Iterator;


public class Selection implements Iterable<Shape> {

    private ArrayList<Shape> selected;


    private Drawing drawing; // Добавляем ссылку на Drawing

    // Конструктор, который принимает Drawing
    public Selection(Drawing drawing)
    {
        this.drawing = drawing;
        selected = new ArrayList<Shape>(0);
    }

    public Selection(ArrayList<Shape> list, Drawing drawing) {
        this.selected = list;
        this.drawing = drawing;
    }


    public void add(Shape s) {
        if (!selected.contains(s)) {
            selected.add(s);
            s.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    public Selection clone() {
        ArrayList<Shape> clone = (ArrayList<Shape>) selected.clone();
        return new Selection(clone,drawing);
    }

    public boolean contains(Shape s) {
        return selected.contains(s);
    }

    public void empty() {
        for (Shape s : selected) {
            s.setSelected(false);
        }
        selected.clear();
    }

    public boolean isEmpty() {
        return selected.isEmpty();
    }

    public Iterator<Shape> iterator() {
        return selected.iterator();
    }

    public int nShapes() {
        return selected.size();
    }

    public String toString() {
        String str;
        str = "Selection contents:\n";
        for (Shape s : selected) {
            str += s.toString() + "\n";
        }
        str += "\n";
        return str;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Selection otherSelection)
        {
            if(this.selected.size() == otherSelection.selected.size())
            {
                boolean result = true;

                for(int i = 0; i < this.selected.size(); i++)
                {
                    result &= this.selected.get(i).equals(otherSelection.selected.get(i));
                }

                return result;
            } else
            {
                return false;
            }
        }
        return super.equals(obj);
    }

    public void move(int x, int y)
    {
        for (Shape s : selected)
        {
            s.move(x,y);
        }
    }

    public void removeShapesDraw()
    {
        if (drawing != null)
        {
            for (Shape s : selected)
            {
                drawing.removeShape(s);
            }
        }
    }

    public void insertShapesDraw()
    {
        if (drawing != null)
        {
            for (Shape s : selected)
            {
                drawing.insertShape(s);
            }
        }
    }


}
