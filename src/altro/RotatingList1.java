package altro;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RotatingList<T> {
    // 2 con List non si può accedere ai metodi di LL
    LinkedList<T> lista;

    public RotatingList(){
        lista=new LinkedList<T>();
    }

    public void add(T elem){
        lista.addLast(elem);
    }

    public void rotateLeft(){
        // 3 usa lista come booleano
        if(!(lista.isEmpty())){
        T elem= lista.get(0);
        lista.removeFirst();
        lista.addLast(elem);
        }
        else throw new RuntimeException("lista vuota");
    }
    public void rotateRight(){
        if(!(lista.isEmpty())){
        T elem=lista.get(lista.size()-1);
        lista.removeLast();
        lista.addFirst(elem);
        }
        // 1 manca ;
        else throw new RuntimeException("lista vuota");
    }

    @Override
    public boolean equals(Object o) {
        // 4 se si casta a LL di object non si possono tirare fuori T
        // 5 bisogna confrontare due RotatingList, perché si controlla LinkedList?
        if(!(o instanceof RotatingList)) return false;
        RotatingList<T> r = (RotatingList) o;
        // 6 iteratori grezzi
        Iterator j = r.lista.iterator();
        Iterator i = lista.iterator();
        while(i.hasNext()){
            T elem=i.next();
            if(!(r.lista.contains(elem))) return false;
        }
        while(j.hasNext()){
            T elem= j.next();
            if(!(lista.contains(elem))) return false;
        }
        return true;
    }

    // 7 toString non è sovrascritto

}
