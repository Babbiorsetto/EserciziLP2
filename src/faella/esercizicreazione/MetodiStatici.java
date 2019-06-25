package faella.esercizicreazione;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;

public class MetodiStatici {

    /*
     * Date due liste dello stesso tipo di oggetti,
     * restituisce una terza lista composta dagli oggetti delle prime due intervallati.
     */
    public static <T> LinkedList<T> merge(LinkedList<T> first, LinkedList<T> second) {

        if (first.size() != second.size()) {
            throw new IllegalArgumentException("Le due liste devono avere pari lunghezza");
        }

        LinkedList<T> result = new LinkedList<>();

        Iterator<T> iter1 = first.iterator();
        Iterator<T> iter2 = second.iterator();

        while (iter1.hasNext()) {
            result.addLast(iter1.next());
            result.addLast(iter2.next());
        }

        return result;
    }

    /*
     * Controlla se prima e seconda sono anagrammi.
     */
    public static boolean anagramma(String prima, String seconda) {
        List<Character> uno = new LinkedList<>();
        List<Character> due = new LinkedList<>();

        if (prima.length() != seconda.length()) return false;

        for (int i = 0; i < prima.length(); i++) {
            uno.add(prima.charAt(i));
        }

        for (int i = 0; i < prima.length(); i++) {
            due.add(seconda.charAt(i));
        }

        boolean trovato;
        for (Character c : uno) {

            Iterator<Character> iter = due.iterator();
            trovato = false;

            while (iter.hasNext() && !trovato) {
                if (c.equals(iter.next())) {
                    iter.remove();
                    trovato = true;
                }
            }

            if (!trovato) return false;
        }

        return true;
    }

    /*
     * Data una collezione e un oggetto di tipo Class x, restituisce un oggetto
     * della collezione il cui tipo effettivo sia esattamente x. Se non esiste
     * un tale oggetto, restituisce null.
     */
    public static <T> Object getByType(Collection<?> c, Class<T> x) {
        for (Object o : c) {
            if (o.getClass() == x) {
                return o;
            }
        }
        return null;
    }

    public static boolean arePermutations(List<?> a, List<?> b) {

        if (a.size() != b.size()) {
            return false;
        }

        List<Object> first = new ArrayList<>(), second = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            first.add(a.get(i));
            second.add(b.get(i));
        }

        boolean removed;
        Object o1;
        Object o2;
        Iterator<Object> iter1 = first.iterator();
        Iterator<Object> iter2;
        while (iter1.hasNext()) {

            o1 = iter1.next();
            iter2 = second.iterator();
            removed = false;

            while (iter2.hasNext()) {

                o2 = iter2.next();

                if (o1.equals(o2)) {
                    iter2.remove();
                    iter1.remove();
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                return false;
            }
        }
        return true;
    }

}
