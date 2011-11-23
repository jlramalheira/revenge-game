/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author marcos
 */
public class Point {
    public double x;
    public double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isZero() {
        return ((x == 0) && (y == 0));
    }
    
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
