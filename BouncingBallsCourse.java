/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           29 March 2024 08:44
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

public class BouncingBallsCourse {

    public static void main(String[] args) {

        int n = Integer.parseInt("4");

        Ball[] balls = new Ball[n];

        for (int i = 0; i < n; i++) {
            balls[i] = new Ball();
        }

        while (true) {
            StdDraw.clear();
            for (int i = 0; i < n; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show();
        }

    }
}
