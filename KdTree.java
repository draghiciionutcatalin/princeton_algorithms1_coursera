/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           30 April 2024 13:12
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private static class Node {
        private final Point2D p;      // the point
        private final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private final boolean isVertical; // indicates if the node splits vertically or horizontally
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect, boolean isVertical) {
            this.p = p;
            this.rect = rect;
            this.isVertical = isVertical;
        }
    }

    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        root = insert(root, p, true, 0, 0, 1, 1);
    }

    private Node insert(Node node, Point2D p, boolean isVertical, double xmin, double ymin,
                        double xmax, double ymax) {
        if (node == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax), isVertical);
        }

        if (node.p.equals(p)) return node;

        if (node.isVertical) {
            if (p.x() < node.p.x())
                node.lb = insert(node.lb, p, !isVertical, xmin, ymin, node.p.x(), ymax);
            else node.rt = insert(node.rt, p, !isVertical, node.p.x(), ymin, xmax, ymax);
        }
        else {
            if (p.y() < node.p.y())
                node.lb = insert(node.lb, p, !isVertical, xmin, ymin, xmax, node.p.y());
            else node.rt = insert(node.rt, p, !isVertical, xmin, node.p.y(), xmax, ymax);
        }

        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null) return false;
        if (node.p.equals(p)) return true;

        if (node.isVertical) {
            if (p.x() < node.p.x()) return contains(node.lb, p);
            else return contains(node.rt, p);
        }
        else {
            if (p.y() < node.p.y()) return contains(node.lb, p);
            else return contains(node.rt, p);
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();

        StdDraw.setPenRadius();
        if (node.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }

        draw(node.lb);
        draw(node.rt);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Rectangle cannot be null");
        List<Point2D> pointsInRange = new LinkedList<>();
        range(root, rect, pointsInRange);
        return pointsInRange;
    }

    private void range(Node node, RectHV rect, List<Point2D> pointsInRange) {
        if (node == null) return;
        if (rect.contains(node.p)) pointsInRange.add(node.p);

        if (node.lb != null && rect.intersects(node.lb.rect)) range(node.lb, rect, pointsInRange);
        if (node.rt != null && rect.intersects(node.rt.rect)) range(node.rt, rect, pointsInRange);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if (isEmpty()) return null;
        return nearest(root, p, root.p);
    }

    private Point2D nearest(Node node, Point2D queryPoint, Point2D champion) {
        if (node == null) return champion;
        if (node.rect.distanceSquaredTo(queryPoint) >= champion.distanceSquaredTo(queryPoint))
            return champion;

        if (node.p.distanceSquaredTo(queryPoint) < champion.distanceSquaredTo(queryPoint))
            champion = node.p;

        Node closerNode = node.lb;
        Node fartherNode = node.rt;

        if (node.rt != null && node.rt.rect.contains(queryPoint)) {
            closerNode = node.rt;
            fartherNode = node.lb;
        }

        champion = nearest(closerNode, queryPoint, champion);
        champion = nearest(fartherNode, queryPoint, champion);

        return champion;
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.1, 0.2));
        kdTree.insert(new Point2D(0.3, 0.4));
        kdTree.insert(new Point2D(0.5, 0.6));
        kdTree.insert(new Point2D(0.7, 0.8));

        Point2D point = new Point2D(0.25, 0.35);
        Point2D nearestPoint = kdTree.nearest(point);
        StdOut.println("Nearest point to " + point + " is " + nearestPoint);
    }

}