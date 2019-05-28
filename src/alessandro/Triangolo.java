package alessandro;

public class Triangolo {

    double a, b, c;

    public Triangolo(double a, double b, double c) {
        if (a < b + c && b < a + c && c < a + b) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            throw new IllegalArgumentException("Non posso creare un triangolo del genere");
        }
    }

    public double getArea() {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }

    public static class Rettangolo extends Triangolo {
        public Rettangolo(double cateto1, double cateto2) {
            super(cateto1, cateto2, Math.sqrt( Math.pow(cateto1, 2) + Math.pow(cateto2, 2) ) );
        }
    }

    public static class Isoscele extends Triangolo {
        public Isoscele(double base, double lato) {
            super(base, lato, lato);
        }
    }
}
