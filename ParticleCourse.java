/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           29 March 2024 09:44
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class ParticleCourse {

    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius = 0.1; // radius
    private final double mass = 0.9; // mass
    private int count; // number of collisions

    public ParticleCourse() {
        this.rx = StdRandom.uniformDouble(0.0, 0.9);
        this.ry = StdRandom.uniformDouble(0.0, 0.9);
        this.vx = StdRandom.uniformDouble(0.0, 0.2);
        this.vy = StdRandom.uniformDouble(0.0, 0.2);
    }

    public void move(double dt) {
    }

    public void draw() {
    }

    public double timeToHit(ParticleCourse that) {
        if (this == that) {
            return Double.MAX_VALUE;
        }
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) {
            return Double.MAX_VALUE;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) return Double.MAX_VALUE;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        return 0.0d;
    }

    public double timeToHitHorizontalWall() {
        return 0.0d;
    }

    public void bounceOff(ParticleCourse that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() {
    }

    public void bounceOffHorizontalWall() {
    }

    public static void main(String[] args) {

    }
}
