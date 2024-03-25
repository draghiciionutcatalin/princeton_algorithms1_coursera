/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           19 March 2024 16:57
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Constructor cannot have null object");
        }

        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point at index " + i + " is null");
            }
        }

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        if (hasDuplicate(sortedPoints)) {
            throw new IllegalArgumentException("Duplicate point found");
        }

        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point p = sortedPoints[i];
            Point[] otherPoints = sortedPoints.clone();
            Arrays.sort(otherPoints, p.slopeOrder());

            int j = 1;
            while (j < n) {
                List<Point> collinearPoints = new ArrayList<>();
                double slope = p.slopeTo(otherPoints[j]);
                collinearPoints.add(p);
                while (j < n && p.slopeTo(otherPoints[j]) == slope) {
                    collinearPoints.add(otherPoints[j++]);
                }

                if (collinearPoints.size() >= 4 && p.compareTo(collinearPoints.get(0)) < 0) {
                    Point minPoint = collinearPoints.get(0);
                    Point maxPoint = collinearPoints.get(collinearPoints.size() - 1);
                    segments.add(new LineSegment(minPoint, maxPoint));
                }
            }
        }

    }

    public int numberOfSegments() {        // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() {       // the line segments
        return segments.toArray(new LineSegment[0]);
    }

    private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
