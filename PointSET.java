/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           30 April 2024 11:08
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PointSET {

    private RedBlackBST<Double, Point2D> rbtree;
    private SET<Point2D> pset;
    private int size;

    public PointSET() {
        pset = new SET<Point2D>();
        rbtree = new RedBlackBST<Double, Point2D>();
        size = 0;
    } // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }  // is the set empty?

    public int size() {
        return size;
    }  // number of points in the set

    public void insert(Point2D p) {
        if (pset.contains(p)) return;
        pset.add(p);
        rbtree.put(p.x(), p);
        size++;
    } // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        return pset.contains(p);
    } // does the set contain point p?

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : pset)
            p.draw();
        StdDraw.show(0);
    } // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> range = new SET<Point2D>();
        for (Point2D p : pset)
            if (rect.contains(p)) {
                range.add(p);
            }
        return range;
    } // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        Point2D nearest = p;
        Double dist = Double.MAX_VALUE;
        for (Point2D it : pset)
            if (p.distanceTo(it) < dist && !it.equals(p)) {
                nearest = it;
                dist = p.distanceTo(it);
            }
        return nearest;
    } // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        PointSET pset = new PointSET();
        Point2D p = new Point2D(0.2, 0.3);
        RectHV rect = new RectHV(0.2, 0.2, 0.6, 0.6);
        pset.insert(p);
        for (int i = 0; i < 1000; i++)
            pset.insert(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
        rect.draw();
        StdDraw.circle(p.x(), p.y(), p.distanceTo(pset.nearest(p)));
        pset.draw();
        StdDraw.show(0);
        StdOut.println("Nearest to " + p.toString() + " = " + pset.nearest(p));
        for (Point2D point : pset.range(rect))
            StdOut.println("In Range: " + point.toString());
    }               // unit testing of the methods (optional)

}
