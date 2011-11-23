/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author marcos
 */
public class Bounds {
    public double left;
    public double right;
    public double top;
    public double bottom;

    public Bounds() {
    }

    public Bounds(double left, double right, double top, double bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public String toString() {
        return "Bounds{" + "left=" + left + ", right=" + right + ", top=" + top + ", bottom=" + bottom + '}';
    }
}
