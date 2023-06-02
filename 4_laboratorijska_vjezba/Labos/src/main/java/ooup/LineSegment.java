package ooup;

public class LineSegment extends AbstractGraphicalObject {
    public LineSegment() {
        super(new Point[] {new Point(0,0), new Point(10,0)});
    }

    public LineSegment(Point start, Point end) {
        super(new Point[] {start, end});
    }
    @Override
    public void translate(Point delta) {
        Point p1 = getHotPoint(0);
        Point p2 = getHotPoint(1);
        Point newPoint1 = p1.translate(delta);
        Point newPoint2 = p2.translate(delta);
        setHotPoint(0,newPoint1);
        setHotPoint(1,newPoint2);
    }

    @Override
    public Rectangle getBoundingBox() {
        Point p1 = getHotPoint(0);
        Point p2 = getHotPoint(1);
        Point startPoint = new Point(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()));
        int width = Math.max(p1.getX() - startPoint.getX(), p2.getX() - startPoint.getX()),
                height = Math.max(p1.getY() - startPoint.getY(), p2.getY() - startPoint.getY());
        return new Rectangle(startPoint.getX(), startPoint.getY(), width, height);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return GeometryUtil.distanceFromLineSegment(getHotPoint(0), getHotPoint(1), mousePoint);

    }

    @Override
    public String getShapeName() {
        return "Linija";
    }

    @Override
    public GraphicalObject duplicate() {
        GraphicalObject object = new LineSegment(getHotPoint(0), getHotPoint(1));
        object.setSelected(isSelected());
        for (int i = 0; i < getNumberOfHotPoints(); i++) {
            object.setHotPoint(i, getHotPoint(i));
            object.setHotPointSelected(i, isHotPointSelected(i));
        }
        return object;
    }

    @Override
    public void render(Renderer r) {
        r.drawLine(getHotPoint(0), getHotPoint(1));
    }

}
