package faella.esercizicreazione;

public class Fraction {

    // numeratore, denominatore
    protected int n, d;

    public Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction myFrac = (Fraction) other;
            ReducedFraction t = new ReducedFraction(this);
            ReducedFraction o = new ReducedFraction(myFrac);
            return t.n == o.n && t.d == o.d;
        }
        return false;
    }

    public Fraction times(Fraction other) {
        return new Fraction(n * other.n, d * other.d);
    }

    @Override
    public String toString() {
        return n + "/" + d;
    }

    public static void main(String[] args) {
        Fraction a = new Fraction(12,30);
        Fraction b = new ReducedFraction(12,30);
        Fraction c = new Fraction(1,4);
        Fraction d = c.times(a);
        System.out.println(a);
        System.out.println(b);
        System.out.println(d);
        System.out.println(a.equals(b));
        System.out.println(c.times(b));
    }

}
