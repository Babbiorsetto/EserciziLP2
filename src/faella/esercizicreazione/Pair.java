package faella.esercizicreazione;

public class Pair<T,E> {
    private T first;
    private E second;

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair)) return false;

        Pair p = (Pair) other;
        if (first.equals(p.first) && second.equals(p.second)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode() ^ second.hashCode();
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "," + second.toString() + ")";
    }

    public static void main(String[] args) {
        Pair<String,Integer> p1 = new Pair<>("uno", 1);
        Pair<String,Integer> p2 = new Pair<>("due", 2);
        Pair<String,Integer> p3 = new Pair<>("uno", 1);
        System.out.println(p1);
        System.out.println(p1.hashCode());
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
    }
}
