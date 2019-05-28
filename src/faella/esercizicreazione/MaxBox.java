package faella.esercizicreazione;

import java.util.*;

public class MaxBox<T> {

    private Comparator<T> comparator = null;
    private T max = null;

    public MaxBox() {

    }

    public MaxBox(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T input) {

        if (null == input) {
            throw new NullPointerException();
        }

        if (null == max) { // On first insert save input. Also if the object does not implement Comparable and there is no specified comparator, throw an exception

            max = input;
            if (!(max instanceof Comparable) && null == comparator) {
                throw new IllegalArgumentException("Parameter does not implement Comparable");
            }

        } else if (null == comparator) { // if there is no specified comparator, then there must be a natural ordering

            Comparable<T> comp = (Comparable<T>) max; // cast to access compareTo method. It's safe because if max was not a comparable, an IllegalArgumentException would have been thrown
            if (comp.compareTo(input) < 0) { // if max (cast to a comparable) is less than input in the natural ordering, input is the new max
                max = input;
            }

        } else { // same as before, but with the ordering of the provided Comparator

            if (comparator.compare(max, input) < 0) {
                max = input;
            }

        }
    }

    public T getMax() {
        if (null == max) {
            throw new NoSuchElementException("this MaxBox is empty");
        }
        return max;
    }
}
