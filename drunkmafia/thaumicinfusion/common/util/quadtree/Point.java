/*
 * Decompiled with CFR 0.152.
 */
package drunkmafia.thaumicinfusion.common.util.quadtree;

public class Point<T>
implements Comparable {
    private double x;
    private double y;
    private T opt_value;

    public Point(double x, double y, T opt_value) {
        this.x = x;
        this.y = y;
        this.opt_value = opt_value;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public T getValue() {
        return this.opt_value;
    }

    public void setValue(T opt_value) {
        this.opt_value = opt_value;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public int compareTo(Object o) {
        Point tmp = (Point)o;
        if (this.x < tmp.x) {
            return -1;
        }
        if (this.x > tmp.x) {
            return 1;
        }
        if (this.y < tmp.y) {
            return -1;
        }
        if (this.y > tmp.y) {
            return 1;
        }
        return 0;
    }
}

