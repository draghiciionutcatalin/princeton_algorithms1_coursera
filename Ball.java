/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           29 March 2024 08:49
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius = 0.05;

    public Ball() {
        this.rx = StdRandom.uniformDouble(0.0, 0.9);
        this.ry = StdRandom.uniformDouble(0.0, 0.9);
        this.vx = StdRandom.uniformDouble(0.0, 0.1);
        this.vy = StdRandom.uniformDouble(0.0, 0.1);
    }

    public void move(double dt) {
        if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) {
            vy = -vy;
        }
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
