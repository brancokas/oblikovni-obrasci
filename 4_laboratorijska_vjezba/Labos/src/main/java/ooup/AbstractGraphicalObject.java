package ooup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphicalObject implements GraphicalObject{
    private boolean selected;
    private Point[] hotPoints;
    private boolean[] hotPointSelected;
    private int hotPointsSize = 0;
    List<GraphicalObjectListener> listeners = new ArrayList<>();

    protected AbstractGraphicalObject(Point[] points) {
        hotPoints = points;
        for (int i = 0; i < hotPoints.length; i++) {
            if (hotPoints[i] != null)
                hotPointsSize++;
            else break;
        }
        hotPointSelected = new boolean[hotPointsSize];
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        notifySelectionListeners();
    }

    @Override
    public Point getHotPoint(int index) {
        if (index >= hotPointsSize || index < 0)
            throw new IndexOutOfBoundsException();
        return hotPoints[index];
    }

    @Override
    public int getNumberOfHotPoints() {
        return hotPointsSize;
    }

    @Override
    public void setHotPoint(int index, Point point) {
        if (index >= hotPointsSize || index < 0)
            throw new IndexOutOfBoundsException();
        hotPoints[index] = point;
        notifyListeners();
    }

    @Override
    public boolean isHotPointSelected(int index) {
        if (index >= hotPointsSize || index < 0)
            throw new IndexOutOfBoundsException();
        return hotPointSelected[index];
    }

    @Override
    public void setHotPointSelected(int index, boolean selected) {
        if (index >= hotPointsSize || index < 0)
            throw new IndexOutOfBoundsException();
        hotPointSelected[index] = selected;
        notifySelectionListeners();
    }

    @Override
    public double getHotPointDistance(int index, Point mousePoint) {
        if (index >= hotPointsSize || index < 0)
            throw new IndexOutOfBoundsException();
        return GeometryUtil.distanceFromPoint(mousePoint, hotPoints[index]);
    }

    @Override
    public void addGraphicalObjectListener(GraphicalObjectListener l) {
        listeners.add(l);
    }

    @Override
    public void removeGraphicalObjectListener(GraphicalObjectListener l) {
        listeners.remove(l);
    }

    protected void notifyListeners() {
        listeners.forEach((graphicalObjectListener) -> graphicalObjectListener.graphicalObjectChanged(this));
    }

    protected void notifySelectionListeners() {
        listeners.forEach((graphicalObjectListener -> graphicalObjectListener.graphicalObjectSelectionChanged(this)));
    }

    @Override
    public String toString() {
        return getShapeName() + ": hp1=" + getHotPoint(0) + ", hp2=" + getHotPoint(1);
    }
}
