package alessandro;

import java.util.List;
import java.util.Iterator;
import java.util.RandomAccess;

public class ListReverser {

    public static <T> void reverse(List <T> l) {

        T temp;

        if (l instanceof RandomAccess) { // se ho get e set a tempo costante

            for (int i = 0; i < (l.size() / 2); i++) {
                temp = l.set(i, l.get( (l.size()-i)-1 ));
                l.set((l.size()-i)-1, temp);
            }

        } else if (l != null) { // se non mi posso affidare a add(index,T) oppure a un ListIterator. Faccio ricorsivo

            // Versione 1: ricorsiva
            Iterator<T> iter = l.iterator();
            if (iter.hasNext()) {
                temp = iter.next();
                iter.remove();
                ListReverser.reverse(l);
                l.add(temp);
            }

        }
    }
}
