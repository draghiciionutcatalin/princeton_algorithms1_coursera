import java.util.Comparator;

public class PointMy implements Comparable<PointMy> {

    private int x;
    private int y;

    public PointMy(int x, int y) {                // constructs the point (x, y)
        if (x < 0 || y < 0 || x > 32767 || y > 32767) {
            throw new IllegalArgumentException("Coordinates out of bounds: between 0 and 32767");
        }
        this.x = x;
        this.y = y;
    }

    public void draw() {                               // draws this point

    }

    public void drawTo(PointMy that) { // draws the line segment from this point to that point

    }

    public String toString() { // string representation
        return "x=" + this.x + " y=" + this.y;
    }

    public static int ccw(Point a, Point b, Point c) {
        double area2 = 1;//(b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) return -1; // clockwise
        else if (area2 > 0) return +1; // counter-clockwise
        else return 0; // collinear
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(PointMy that) {
        double dy1 = this.y - that.y;
        double dy2 = this.y - y;

       /* if (dy1 == 0 && dy2 == 0) { ... }
        else if (dy1 >= 0 && dy2 < 0) return -1;
        else if (dy2 >= 0 && dy1 < 0) return +1;
        else return -ccw(Point2D.this, q1, q2);*/
        return 0;
    }

    public double slopeTo(PointMy that) {  // the slope between this point and that point
        return 0.0;
    }

    public Comparator<PointMy> slopeOrder() { // compare two points by slopes they make with this point
        return null;
    }

    public static void main(String[] args) {

    }
}
