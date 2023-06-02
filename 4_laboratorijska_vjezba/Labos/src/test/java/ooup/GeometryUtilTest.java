package ooup;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeometryUtilTest {
    @Test
    void distanceFromLine1() {
        Point s = new Point(0,0), e = new Point(20, 0), p = new Point(10,10);
        Assertions.assertEquals(10, GeometryUtil.distanceFromLineSegment(s,e,p));
    }

    @Test
    void distanceFromLine2() {
        Point s = new Point(0,0), e = new Point(20, 0), p = new Point(10,0);
        Assertions.assertEquals(0, GeometryUtil.distanceFromLineSegment(s,e,p));
    }
    @Test
    void distanceFromLine3() {
        Point s = new Point(0,0), e = new Point(10, 10), p = new Point(20,20);
        Assertions.assertEquals(GeometryUtil.distanceFromPoint(s,e), GeometryUtil.distanceFromLineSegment(s,e,p));
    }
}