package faella.esercizicreazione;

public class Interval {
    private double inf;
    protected boolean infClosed;
    private double sup;
    protected boolean supClosed;

    private Interval() {

    }

    protected Interval(double inf, double sup) {
        if (inf > sup) {
            throw new IllegalArgumentException("Il limite inferiore non puo' essere maggiore del limite superiore");
        }
        this.inf = inf;
        this.sup = sup;
    }

    public static class Open extends Interval {
        public Open(double inf, double sup) {
            super(inf, sup);
            infClosed = false;
            supClosed = false;
        }
    }

    public static class Closed extends Interval {
        public Closed(double inf, double sup) {
            super(inf, sup);
            infClosed = true;
            supClosed = true;
        }
    }

    public boolean contains(int x) {
        boolean inInf, inSup;

        inInf = infClosed ? x >= inf : x > inf;
        inSup = supClosed ? x <= sup : x < sup;
        return inInf && inSup;
    }

    public Interval join(Interval other) {

        boolean invalid = false;
        if (supClosed || other.infClosed) {
            if (sup < other.inf) {
                invalid = true;
            }
        } else {
            if (sup <= other.inf) {
                invalid = true;
            }
        }

        if (infClosed || other.supClosed) {
            if (inf > other.sup) {
                invalid = true;
            }
        } else {
            if (inf >= other.sup) {
                invalid = true;
            }
        }

        if (invalid) throw new IllegalArgumentException("I due intervalli non si sovrappongono");

        Interval nuovo = new Interval();

        if(inf < other.inf) {
            nuovo.inf = inf;
            nuovo.infClosed = infClosed;
        } else if (inf > other.inf) {
            nuovo.inf = other.inf;
            nuovo.infClosed = other.infClosed;
        } else {
            nuovo.inf = inf;
            nuovo.infClosed = infClosed || other.infClosed;
        }

        if (sup > other.sup) {
            nuovo.sup = sup;
            nuovo.supClosed = supClosed;
        } else if (sup < other.sup) {
            nuovo.sup = other.sup;
            nuovo.supClosed = other.supClosed;
        } else {
            nuovo.sup = sup;
            nuovo.supClosed = supClosed || other.supClosed;
        }

        return nuovo;
    }

    public String toString() {
        String parentesiSx = "(";
        String parentesiDx = ")";
        if(infClosed) {
            parentesiSx = "[";
        }
        if(supClosed) {
            parentesiDx = "]";
        }
        return parentesiSx + inf + ", " + sup + parentesiDx;
    }

    public static void main(String[] args) {
        Interval i1 = new Interval.Open(5, 10.5);
        Interval i2 = new Interval.Closed(7, 20);
        Interval primo = new Interval.Open(3, 5);
        Interval secondo = new Interval.Open(5, 7);

        Interval i3 = i1.join(i2);
        try {
            Interval fallito = primo.join(secondo);
        } catch (Exception e) {
            System.out.println("uh oh!");
        }
        System.out.println(i1.contains(5));
        System.out.println(i2.contains(20));
        System.out.println(i1) ;
        System.out.println(i2) ;
        System.out.println(i3) ;

    }

}
