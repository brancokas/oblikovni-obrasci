package ooup;

public class GeometryUtil {

    public static double distanceFromPoint(Point point1, Point point2) {
        // izračunaj euklidsku udaljenost između dvije točke ...
        int x = point1.getX() - point2.getX(), y = point1.getY() - point2.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double distanceFromLineSegment(Point s, Point e, Point p) {
        // Izračunaj koliko je točka P udaljena od linijskog segmenta određenog
        // početnom točkom S i završnom točkom E. Uočite: ako je točka P iznad/ispod
        // tog segmenta, ova udaljenost je udaljenost okomice spuštene iz P na S-E.
        // Ako je točka P "prije" točke S ili "iza" točke E, udaljenost odgovara
        // udaljenosti od P do početne/konačne točke segmenta.

        double a, b, c;

        a = distanceFromPoint(e,p);
        b = distanceFromPoint(s,p);
        c = distanceFromPoint(s,e);
        double alfa = calculateAngle(b,c,a), beta = calculateAngle(c,a,b);
        if (alfa >= 90.0)
            return b;
        if (beta >= 90.0)
            return a;
        if (Double.isNaN(alfa) || Double.isNaN(beta)) {
            if (a + b - distanceFromPoint(s,e) < 1e-8)
                return 0;
            return Math.min(a,b);
        }

        double k;
        k = (e.getY() - s.getY()) / (e.getX() - s.getX());
        a = -k;
        b = 1;
        c = k * s.getX() - s.getY();
        return Math.abs(a * p.getX() + b * p.getY() + c) / Math.sqrt(a * a + b * b);
    }

    private static double calculateAngle(double first, double second, double third) {
        return Math.acos(first * first + second * second - third * third / (2* first * second));
    }
}
