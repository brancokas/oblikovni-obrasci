package ooup;

import java.awt.*;

public class G2DRendererImpl implements Renderer {
    private Graphics2D graphics2D;
    public G2DRendererImpl(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    @Override
    public void drawLine(Point s, Point e) {
        graphics2D.setColor(Color.BLUE);
        graphics2D.drawLine(s.getX(), s.getY(), e.getX(), e.getY());
    }

    @Override
    public void fillPolygon(Point[] points) {
        graphics2D.setColor(Color.BLUE);
        int n = points.length;
        int[] x = new int[n], y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = points[i].getX();
            y[i] = points[i].getY();
        }
        graphics2D.fillPolygon(x, y, n);
        graphics2D.setColor(Color.red);
        graphics2D.drawPolygon(x, y, n);
    }
}
