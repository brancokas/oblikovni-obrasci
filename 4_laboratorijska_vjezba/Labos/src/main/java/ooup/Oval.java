package ooup;


import static ooup.GeometryUtil.distanceFromPoint;
import static ooup.GeometryUtil.distanceFromLineSegment;

public class Oval extends AbstractGraphicalObject {
    private Point center;

    public Oval() {
        this(new Point(10,0), new Point(0,10));
    }

    public Oval(Point rightHP, Point downHP) {
        super(new Point[]{rightHP, downHP});
        center = calculateCenter();
    }

    private Point calculateCenter() {
        Point rightHP = getHotPoint(0);
        Point bottomHP = getHotPoint(1);
        int radiusX = rightHP.getX() - bottomHP.getX();
        int radiusY = bottomHP.getY() - rightHP.getY();
        return new Point(rightHP.getX() - radiusX, bottomHP.getY() - radiusY);
    }

    @Override
    public void translate(Point delta) {
        Point p1 = getHotPoint(0);
        Point p2 = getHotPoint(1);
        Point newPoint1 = p1.translate(delta);
        Point newPoint2 = p2.translate(delta);
        setHotPoint(0,newPoint1);
        setHotPoint(1,newPoint2);
        center = calculateCenter();
    }

    @Override
    public Rectangle getBoundingBox() {
        Point rightHP = getHotPoint(0);
        Point bottomHP = getHotPoint(1);
        int radiusX = rightHP.getX() - bottomHP.getX();
        int radiusY = bottomHP.getY() - rightHP.getY();
        return new Rectangle(rightHP.getX() - 2*radiusX, bottomHP.getY() - 2*radiusY,
                2*radiusX, 2*radiusY);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        Point rightHP = getHotPoint(0);
        Point bottomHP = getHotPoint(1);
        int radiusX = rightHP.getX() - bottomHP.getX();
        int radiusY = bottomHP.getY() - rightHP.getY();
        double distance = distanceFromPoint(calculateCenter(), mousePoint);
        if (distance < 2*radiusX || distance < 2*radiusY)
            return 0;

        Rectangle rectangle = getBoundingBox();
        Point p1 = new Point(rectangle.getX(), rectangle.getY());
        Point p2 = new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY());
        Point p3 = new Point(rectangle.getX(), rectangle.getY() + rectangle.getHeight());
        Point p4 = new Point(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight());

        double ap = distanceFromLineSegment(p1,p2,mousePoint), bp = distanceFromLineSegment(p1,p3,mousePoint),
                cp = distanceFromLineSegment(p3,p4,mousePoint), dp = distanceFromLineSegment(p2,p4,mousePoint);

        return Math.min(Math.min(Math.min(ap, bp), cp), dp);
    }

    @Override
    public String getShapeName() {
        return "Oval";
    }

    @Override
    public GraphicalObject duplicate() {
        GraphicalObject object = new Oval(getHotPoint(0), getHotPoint(1));
        object.setSelected(isSelected());
        for (int i = 0; i < getNumberOfHotPoints(); i++) {
            object.setHotPoint(i, getHotPoint(i));
            object.setHotPointSelected(i, isHotPointSelected(i));
        }
        return object;
    }

    @Override
    public void render(Renderer r) {
        Point center = calculateCenter();
        Point widhei = widthHeight();
        double radiusX = widhei.getX(), radiusY = widhei.getY();
        Point[] points = new Point[180];

        for (int i = 1; i <= 180; i++) {
            double kut = 2*Math.PI * i / 180;

            int x = (int) (center.getX() + radiusX * Math.cos(kut) + 0.5);
            int y = (int) (center.getY() + radiusY * Math.sin(kut) + 0.5);

            points[i-1] = new Point(x,y);
        }
        r.fillPolygon(points);
    }

    private Point widthHeight() {
        Point rightHP = getHotPoint(0);
        Point bottomHP = getHotPoint(1);
        int radiusX = rightHP.getX() - bottomHP.getX();
        int radiusY = bottomHP.getY() - rightHP.getY();
        return new Point(radiusX, radiusY);
    }
}
