package altro;

import java.util.ArrayList;

public class RotatingList<T> {
    // 7 visibilità
    ArrayList<T> rotList;

    public RotatingList() {
        rotList = new ArrayList<>();
    }

    public void add(T item) {
        rotList.add(item);
    }

    // Per ruotare a sx tolgo il primo e lo reinserisco
    public void rotateLeft() {
        // 0.1 di nuovo molto inefficiente
        T elem = rotList.get(0);
        // 1 remove restituisce l'elemento tolto, perché usare anche get? Lo rifà anche dopo
        rotList.remove(0);
        rotList.add(elem);
    }

    // Per ruotare a dx tolgo l'ultimo e creo una nuova lista
    public void rotateRight() {
        T elem = rotList.get(rotList.size() - 1);
        rotList.remove(rotList.size() - 1);
        ArrayList<T> newList = new ArrayList<>();
        newList.add(elem);
        for(T t : rotList) {
            newList.add(t);
        }
        rotList = newList;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof RotatingList)) {
            // 5 ritornare false no, eh?
            throw new IllegalArgumentException();
        }
        // 2 cast sbagliato, RotatingList grezzo
        RotatingList other = (RotatingList) o;

        // 3 controllo sbagliato, non controlla che siano equals secondo la specifica. Lo so fin troppo bene
        for(T t : rotList) {
            // 4 non other.contains, che non significa niente
            if(!(other.rotList.contains(t))) {
                return false;
            }
        }
        return true;
    }

    // 6 manca toString
    @Override
    public String toString() {
        return rotList.toString();
    }
}
