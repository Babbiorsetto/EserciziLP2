public class RotatingList<T> {

    private LinkedList<T> lista;

    public RotatingList() {
        lista = new LinkedList<T>();
    }

    public void add(T toAdd) {
        lista.add(toAdd);
    }

    public void rotateLeft() {
        T temp = lista.removeFirst();
        lista.addLast(temp);
    }

    public void rotateRight() {
        T temp = lista.removeLast();
        lista.addFirst(temp);
    }

    public String toString() {
        return lista.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof RotatingList)) return false;

        boolean ret = true;
        for (T obj : lista) {
            if (!(other.lista.contains(obj))) {
                ret = false;
                break;
            }
        }
        return ret;
    }

}
