/*
 * Decompiled with CFR 0.152.
 */
package drunkmafia.thaumicinfusion.common.util.quadtree;

import drunkmafia.thaumicinfusion.common.util.quadtree.Node;
import drunkmafia.thaumicinfusion.common.util.quadtree.Point;
import drunkmafia.thaumicinfusion.common.util.quadtree.QuadTreeException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuadTree<T> {
    private final Class<T> tClass;
    private final Node root_;
    private int count_;

    public QuadTree(Class<T> tClass, double minX, double minY, double maxX, double maxY) {
        this.tClass = tClass;
        this.root_ = new Node(minX, minY, maxX - minX, maxY - minY, null);
    }

    public Node getRootNode() {
        return this.root_;
    }

    public void set(double x, double y, T value) {
        Node root = this.root_;
        if (x < root.getX() || y < root.getY() || x > root.getX() + root.getW() || y > root.getY() + root.getH()) {
            throw new QuadTreeException("Out of bounds : (" + x + ", " + y + ")");
        }
        if (this.insert(root, new Point<T>(x, y, value))) {
            ++this.count_;
        }
    }

    public T get(double x, double y, T opt_default) {
        Node node = this.find(this.root_, x, y);
        return node != null ? node.getPoint().getValue() : opt_default;
    }

    public Object remove(double x, double y) {
        Node node = this.find(this.root_, x, y);
        if (node != null) {
            Object value = node.getPoint().getValue();
            node.setPoint(null);
            node.setNodeType(Node.NodeType.EMPTY);
            this.balance(node);
            --this.count_;
            return value;
        }
        return null;
    }

    public boolean contains(double x, double y) {
        return this.get(x, y, null) != null;
    }

    public boolean isEmpty() {
        return this.root_.getNodeType() == Node.NodeType.EMPTY;
    }

    public int getCount() {
        return this.count_;
    }

    public void clear() {
        this.root_.setNw(null);
        this.root_.setNe(null);
        this.root_.setSw(null);
        this.root_.setSe(null);
        this.root_.setNodeType(Node.NodeType.EMPTY);
        this.root_.setPoint(null);
        this.count_ = 0;
    }

    public Point[] getKeys() {
        final ArrayList arr = new ArrayList();
        this.traverse(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                arr.add(node.getPoint());
            }
        });
        return arr.toArray(new Point[arr.size()]);
    }

    public T[] getValues() {
        final ArrayList arr = new ArrayList();
        this.traverse(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                arr.add(node.getPoint().getValue());
            }
        });
        return arr.toArray((Object[])Array.newInstance(this.tClass, arr.size()));
    }

    public Point<T>[] searchIntersect(final double xmin, final double ymin, final double xmax, final double ymax) {
        final ArrayList arr = new ArrayList();
        this.navigate(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                Point pt = node.getPoint();
                if (pt.getX() >= xmin && pt.getX() <= xmax && pt.getY() >= ymin && pt.getY() <= ymax) {
                    arr.add(node.getPoint());
                }
            }
        }, xmin, ymin, xmax, ymax);
        return arr.toArray(new Point[arr.size()]);
    }

    public Point<T>[] searchWithin(final double xmin, final double ymin, final double xmax, final double ymax) {
        final ArrayList arr = new ArrayList();
        this.navigate(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                Point pt = node.getPoint();
                if (pt.getX() > xmin && pt.getX() < xmax && pt.getY() > ymin && pt.getY() < ymax) {
                    arr.add(node.getPoint());
                }
            }
        }, xmin, ymin, xmax, ymax);
        return arr.toArray(new Point[arr.size()]);
    }

    public List<T> searchWithinObject(final double xmin, final double ymin, final double xmax, final double ymax) {
        final ArrayList arr = new ArrayList();
        this.navigate(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                Point pt = node.getPoint();
                if (pt.getX() >= xmin && pt.getX() <= xmax && pt.getY() >= ymin && pt.getY() <= ymax) {
                    arr.add(node.getPoint().getValue());
                }
            }
        }, xmin, ymin, xmax, ymax);
        return arr;
    }

    public void navigate(Node node, Func func, double xmin, double ymin, double xmax, double ymax) {
        switch (node.getNodeType()) {
            case LEAF: {
                func.call(this, node);
                break;
            }
            case POINTER: {
                if (this.intersects(xmin, ymax, xmax, ymin, node.getNe())) {
                    this.navigate(node.getNe(), func, xmin, ymin, xmax, ymax);
                }
                if (this.intersects(xmin, ymax, xmax, ymin, node.getSe())) {
                    this.navigate(node.getSe(), func, xmin, ymin, xmax, ymax);
                }
                if (this.intersects(xmin, ymax, xmax, ymin, node.getSw())) {
                    this.navigate(node.getSw(), func, xmin, ymin, xmax, ymax);
                }
                if (!this.intersects(xmin, ymax, xmax, ymin, node.getNw())) break;
                this.navigate(node.getNw(), func, xmin, ymin, xmax, ymax);
            }
        }
    }

    private boolean intersects(double left, double bottom, double right, double top, Node node) {
        return !(node.getX() > right || node.getX() + node.getW() < left || node.getY() > bottom || node.getY() + node.getH() < top);
    }

    public QuadTree clone() {
        try {
            super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        double x1 = this.root_.getX();
        double y1 = this.root_.getY();
        double x2 = x1 + this.root_.getW();
        double y2 = y1 + this.root_.getH();
        final QuadTree<T> clone = new QuadTree<T>(this.tClass, x1, y1, x2, y2);
        this.traverse(this.root_, new Func(){

            @Override
            public void call(QuadTree quadTree, Node node) {
                clone.set(node.getPoint().getX(), node.getPoint().getY(), node.getPoint().getValue());
            }
        });
        return clone;
    }

    public void traverse(Node node, Func func) {
        switch (node.getNodeType()) {
            case LEAF: {
                func.call(this, node);
                break;
            }
            case POINTER: {
                this.traverse(node.getNe(), func);
                this.traverse(node.getSe(), func);
                this.traverse(node.getSw(), func);
                this.traverse(node.getNw(), func);
            }
        }
    }

    public Node find(Node node, double x, double y) {
        Node resposne = null;
        switch (node.getNodeType()) {
            case EMPTY: {
                break;
            }
            case LEAF: {
                if (node.getPoint() == null) {
                    return null;
                }
                resposne = node.getPoint().getX() == x && node.getPoint().getY() == y ? node : null;
                break;
            }
            case POINTER: {
                resposne = this.find(this.getQuadrantForPoint(node, x, y), x, y);
                break;
            }
            default: {
                throw new QuadTreeException("Invalid nodeType");
            }
        }
        return resposne;
    }

    private boolean insert(Node parent, Point point) {
        Boolean result;
        switch (parent.getNodeType()) {
            case EMPTY: {
                this.setPointForNode(parent, point);
                result = true;
                break;
            }
            case LEAF: {
                if (parent.getPoint().getX() == point.getX() && parent.getPoint().getY() == point.getY()) {
                    this.setPointForNode(parent, point);
                    result = false;
                    break;
                }
                this.split(parent);
                result = this.insert(parent, point);
                break;
            }
            case POINTER: {
                result = this.insert(this.getQuadrantForPoint(parent, point.getX(), point.getY()), point);
                break;
            }
            default: {
                throw new QuadTreeException("Invalid nodeType in parent");
            }
        }
        return result;
    }

    private void split(Node node) {
        Point oldPoint = node.getPoint();
        node.setPoint(null);
        node.setNodeType(Node.NodeType.POINTER);
        double x = node.getX();
        double y = node.getY();
        double hw = node.getW() / 2.0;
        double hh = node.getH() / 2.0;
        node.setNw(new Node(x, y, hw, hh, node));
        node.setNe(new Node(x + hw, y, hw, hh, node));
        node.setSw(new Node(x, y + hh, hw, hh, node));
        node.setSe(new Node(x + hw, y + hh, hw, hh, node));
        this.insert(node, oldPoint);
    }

    private void balance(Node node) {
        switch (node.getNodeType()) {
            case LEAF: 
            case EMPTY: {
                if (node.getParent() == null) break;
                this.balance(node.getParent());
                break;
            }
            case POINTER: {
                Node nw = node.getNw();
                Node ne = node.getNe();
                Node sw = node.getSw();
                Node se = node.getSe();
                Node firstLeaf = null;
                if (nw.getNodeType() != Node.NodeType.EMPTY) {
                    firstLeaf = nw;
                }
                if (ne.getNodeType() != Node.NodeType.EMPTY) {
                    if (firstLeaf != null) break;
                    firstLeaf = ne;
                }
                if (sw.getNodeType() != Node.NodeType.EMPTY) {
                    if (firstLeaf != null) break;
                    firstLeaf = sw;
                }
                if (se.getNodeType() != Node.NodeType.EMPTY) {
                    if (firstLeaf != null) break;
                    firstLeaf = se;
                }
                if (firstLeaf == null) {
                    node.setNodeType(Node.NodeType.EMPTY);
                    node.setNw(null);
                    node.setNe(null);
                    node.setSw(null);
                    node.setSe(null);
                } else {
                    if (firstLeaf.getNodeType() == Node.NodeType.POINTER) break;
                    node.setNodeType(Node.NodeType.LEAF);
                    node.setNw(null);
                    node.setNe(null);
                    node.setSw(null);
                    node.setSe(null);
                    node.setPoint(firstLeaf.getPoint());
                }
                if (node.getParent() == null) break;
                this.balance(node.getParent());
            }
        }
    }

    private Node getQuadrantForPoint(Node parent, double x, double y) {
        double mx = parent.getX() + parent.getW() / 2.0;
        double my = parent.getY() + parent.getH() / 2.0;
        if (x < mx) {
            return y < my ? parent.getNw() : parent.getSw();
        }
        return y < my ? parent.getNe() : parent.getSe();
    }

    private void setPointForNode(Node node, Point point) {
        if (node.getNodeType() == Node.NodeType.POINTER) {
            throw new QuadTreeException("Can not set point for node of type POINTER");
        }
        node.setNodeType(Node.NodeType.LEAF);
        node.setPoint(point);
    }

    public static interface Func {
        public void call(QuadTree var1, Node var2);
    }
}

