package faella.esercizicreazione;

import java.util.LinkedList;

public class RotatingList<T> extends LinkedList<T> {

    public void rotateLeft() {
        T temp = removeFirst();
        addLast(temp);
    }

    public void rotateRight() {
        T temp = removeLast();
        addFirst(temp);
    }
    // uguali se contengono gli stessi elementi anche in molteplicita' diversa
    public boolean equals(Object other) {
        if (!(other instanceof RotatingList)) return false;
        @SuppressWarnings("unchecked") RotatingList<T> r = (RotatingList) other;

        boolean ret = true;
        for (T obj : this) {
            if (!(r.contains(obj))) {
                ret = false;
                break;
            }
        }
        if (ret) {
            for (T obj: r) {
                if (!(this.contains(obj))) {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }

}
