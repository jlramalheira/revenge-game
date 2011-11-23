/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

/**
 *
 * @author Marcos
 */
public class Rectangle {
    public double x;
    public double y;
    public double width;
    public double height;

    public Rectangle() {
    }

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return "Rect{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + '}';
    }
}
