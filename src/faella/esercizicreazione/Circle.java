package faella.esercizicreazione;

public class Circle extends Shape {

    private double centroX;
    private double centroY;
    private double raggio;

    public Circle(double centroX, double centroY, double raggio) {
        super(centroX - raggio, centroY - raggio, 2 * raggio, 2 * raggio);
        this.centroX = centroX;
        this.centroY = centroY;
        this.raggio = raggio;
    }

    public void setRadius(double raggio) {
        x = centroX - raggio;
        y = centroY - raggio;
        w = h =  2 * raggio;
        this.raggio = raggio;
    }

    public boolean equals(Object other) {
        if (other instanceof Circle) {
            Circle c = (Circle) other;
            if (centroX == c.centroX && centroY == c.centroY && raggio == c.raggio) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Shape c1 = new Circle(2.0, 3.0, 1.0);
        Shape c2 = new Circle(2.0, 3.0, 1.0);
        System.out.println(c1.posX() + ", " + c1.posY());
        System.out.println(c1.width() + ", " + c1.height());
        System.out.println(c1.equals(c2));
        (( Circle ) c2).setRadius(2.0);
        System.out.println(c2.posX() + ", " + c2.posY());
    }
}
