package faella.esercizicreazione;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * La classe BinRel rappresenta una relazione binaria su un insieme.
 *
 * @param <T> Il tipo di oggetti su cui è definita la relazione.
 */
public class BinRel<T> {

    private Map<T, Set<T>> associazioni = new HashMap<T, Set<T>>();

    /**
     * Aggiunge una coppia di oggetti alla relazione, se la coppia non è già presente.
     * Dopo una chiamata ad addPair(primo, secondo), varrà (primo R secondo).
     */
    public void addPair(T first, T second) {
        Set<T> mySet;
        if ((mySet = associazioni.get(first)) == null) {
            mySet = new HashSet<T>();
            associazioni.put(first, mySet);
        }
        mySet.add(second);
    }

    /**
     * Restituisce true se (first R second), false altrimenti.
     */
    public boolean areRelated(T first, T second) {
        Set<T> mySet;
        if ((mySet = associazioni.get(first)) != null) {
            return mySet.contains(second);
        }
        return false;
    }

    /**
     * Restituisce true se questa relazione binaria è simmetrica, false altrimenti.
     * https://it.wikipedia.org/wiki/Relazione_simmetrica
     */
    public boolean isSymmetric() {
        Set<T> mySet;
        // per ogni elemento x in relazione con qualcosa
        for (T x : associazioni.keySet()) {
            // prende l'insieme dei qualcosa
            mySet = associazioni.get(x);
            // per ogni y nei qualcosa
            for (T y : mySet) {
                // se y non è in relazione con niente, o comunque non con x
                if (associazioni.get(y) == null ||
                    !associazioni.get(y).contains(x)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        BinRel<String> rel = new BinRel<>();
        rel.addPair("a", "albero");
        rel.addPair("a", "amaca");
        System.out.println(rel.isSymmetric());
        rel.addPair("albero", "a");
        rel.addPair("amaca", "a");
        System.out.println(rel.isSymmetric());
        System.out.println(rel.areRelated("a", "amaca"));

    }
}
