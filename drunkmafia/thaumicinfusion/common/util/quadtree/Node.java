/*
 * Decompiled with CFR 0.152.
 */
package drunkmafia.thaumicinfusion.common.util.quadtree;

import drunkmafia.thaumicinfusion.common.util.quadtree.Point;

public class Node {
    private double x;
    private double y;
    private double w;
    private double h;
    private Node opt_parent;
    private Point point;
    private NodeType nodetype = NodeType.EMPTY;
    private Node nw;
    private Node ne;
    private Node sw;
    private Node se;

    public Node(double x, double y, double w, double h, Node opt_parent) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.opt_parent = opt_parent;
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

    public double getW() {
        return this.w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return this.h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Node getParent() {
        return this.opt_parent;
    }

    public void setParent(Node opt_parent) {
        this.opt_parent = opt_parent;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public NodeType getNodeType() {
        return this.nodetype;
    }

    public void setNodeType(NodeType nodetype) {
        this.nodetype = nodetype;
    }

    public Node getNe() {
        return this.ne;
    }

    public void setNe(Node ne) {
        this.ne = ne;
    }

    public Node getNw() {
        return this.nw;
    }

    public void setNw(Node nw) {
        this.nw = nw;
    }

    public Node getSw() {
        return this.sw;
    }

    public void setSw(Node sw) {
        this.sw = sw;
    }

    public Node getSe() {
        return this.se;
    }

    public void setSe(Node se) {
        this.se = se;
    }

    public static enum NodeType {
        EMPTY,
        LEAF,
        POINTER;

    }
}

