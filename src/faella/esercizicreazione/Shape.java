package faella.esercizicreazione;

public abstract class Shape implements Comparable<Shape> {
    protected double x;
    protected double y;
    protected double w;
    protected double h;

    protected Shape(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public double width() {
        return w;
    }
    public double height() {
        return h;
    }
    public double posX() {
        return x;
    }
    public double posY() {
        return y;
    }

    public int compareTo(Shape other) {
        double differenza = w * h - other.w * other.h;
        if (differenza < 0) {
            return -1;
        } else if (differenza > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
