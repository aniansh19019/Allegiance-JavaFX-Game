package util;

public class LineSegment
{
    private Vector a; // one end
    private Vector b; // other end

    public LineSegment(double ax, double ay, double bx, double by)
    {
        this.a = new Vector(ax, ay);
        this.b = new Vector(bx, by);
    }

    public LineSegment(Vector a, Vector b)
    {
        this.a = a;
        this.b = b;
    }

    public Vector getA()
    {
        return a;
    }

    public void setA(Vector a)
    {
        this.a = a;
    }

    public Vector getB()
    {
        return b;
    }

    public void setB(Vector b)
    {
        this.b = b;
    }

    public double getLength()
    {
        return this.a.distanceTo(this.b);
    }

    public boolean intersect(LineSegment other)
    {
        return doIntersect(this.a, this.b, other.a, other.b);
    }

    //the following three functions have been taken from a GeeksForGeeks Article
    //‘How to check if two given line segments intersect?’, GeeksforGeeks, Jul. 10, 2013. https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/ (accessed Dec. 05, 2020).

    static boolean onSegment(Vector p, Vector q, Vector r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
            return true;

        return false;
    }

    static int orientation(Vector p, Vector q, Vector r)
    {
        // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0; // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    static boolean doIntersect(Vector p1, Vector q1, Vector p2, Vector q2)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    @Override
    public String toString()
    {
        return "LineSegment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}