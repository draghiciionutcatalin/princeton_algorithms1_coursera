/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           19 March 2024 16:57
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The input points array is null");
        }

        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point at index " + i + " is null");
            }
        }

        segments = new ArrayList<>();
        Point[] copy = points.clone();
        Arrays.sort(copy);

        int length = copy.length;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int m = k + 1; m < length; m++) {
                        if (copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k])
                                && copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[m])) {
                            segments.add(new LineSegment(copy[i], copy[m]));
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() { // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() { // the line segments
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

    }
}
