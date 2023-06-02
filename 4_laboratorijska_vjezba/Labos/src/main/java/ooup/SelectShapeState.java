package ooup;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectShapeState implements State{
    private List<GraphicalObject> objects = new ArrayList<>();
    private DocumentModel model;

    public SelectShapeState(DocumentModel model) {
        this.model = model;
    }

    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        GraphicalObject object = model.findSelectedGraphicalObject(mousePoint);
//        if (object != null && !ctrlDown && objects.size()==1 && objects.contains(object))
//            return;
        if (!ctrlDown && !objects.isEmpty() || object == null) {
            objects.forEach((graphicalObject) -> graphicalObject.setSelected(false));
            objects.clear();
        }
        if (object == null)
            return;
        object.setSelected(!object.isSelected());
        objects.add(object);
    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

    }

    @Override
    public void mouseDragged(Point mousePoint) {
        if (objects.size() != 1)
            return;

        GraphicalObject go = objects.get(0);
        int index = model.findSelectedHotPoint(go, mousePoint);

        if (index == -1) {

            return;
        }

        go.setHotPoint(index, mousePoint);

    }

    @Override
    public void keyPressed(int keyCode) {
        System.out.println(keyCode + " " + KeyEvent.VK_DOWN);
        if (keyCode == KeyEvent.VK_DOWN)
            objects.forEach((graphicalObject -> graphicalObject.translate(new Point(0,1))));
        else if (keyCode == KeyEvent.VK_UP)
            objects.forEach((graphicalObject -> graphicalObject.translate(new Point(0,-1))));
        else if (keyCode == KeyEvent.VK_LEFT)
            objects.forEach((graphicalObject -> graphicalObject.translate(new Point(-1,0))));
        else if (keyCode == KeyEvent.VK_RIGHT)
            objects.forEach((graphicalObject -> graphicalObject.translate(new Point(1,0))));
        else if (keyCode == KeyEvent.VK_ADD)
            objects.forEach((graphicalObject -> model.increaseZ(graphicalObject)));
        else if (keyCode == KeyEvent.VK_SUBTRACT)
            objects.forEach((graphicalObject -> model.decreaseZ(graphicalObject)));
    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {
        if (!go.isSelected())
            return;
        Rectangle rectangle = go.getBoundingBox();
        drawRectangle(r, rectangle);

        if (objects.size() == 1) {
            for (int i = 0; i < go.getNumberOfHotPoints(); i++) {
                Point p = go.getHotPoint(i);
                rectangle = new Rectangle(p.getX()-2, p.getY() - 2, 4,4);
                drawRectangle(r, rectangle);
            }
        }
    }

    private void drawRectangle(Renderer r, Rectangle rectangle) {
        r.drawLine(new Point(rectangle.getX(), rectangle.getY()), new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY()));
        r.drawLine(new Point(rectangle.getX(), rectangle.getY()), new Point(rectangle.getX(), rectangle.getY() + rectangle.getHeight()));
        r.drawLine(new Point(rectangle.getX(), rectangle.getY() + rectangle.getHeight()), new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()));
        r.drawLine(new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY()), new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()));

    }

    @Override
    public void afterDraw(Renderer r) {

    }

    @Override
    public void onLeaving() {

    }
}
