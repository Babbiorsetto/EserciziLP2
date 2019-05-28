package altro;

import java.util.ArrayList;
import java.util.*;

// 1 classe top-level non public
public class RotatingList<T>{
    // 2 variabile lista public e static che poi è anche di tipo T e non ha senso
	public ArrayList<T> lista;

	public RotatingList(){
		lista = new ArrayList<T>();
	}

	public boolean add(T o){
		return lista.add(o);
	}

	public void rotateLeft(){
        // 3 lista.get(lista.size()) è una IndexOutOfBounds
		T pre=lista.get(lista.size()-1);

		for(int i = lista.size()-2;i>=0;i--){
			pre=lista.set(i,pre);
		}
        // 5 che cambia a fare il valore di pre all'ultimo assegnamento?
		pre=lista.set(lista.size()-1,pre);
	}

	public void rotateRight(){
		T pre=lista.get(0);
        // 6 condizione sbagliata nel for: i > lista.size()
		for(int i = 1;i<lista.size();i++){
			pre=lista.set(i,pre);
		}

		pre=lista.set(0,pre);
	}

	@Override
	public boolean equals(Object o){
        // 4 ridondante
        if(o==null) return false;
		if(!(o instanceof RotatingList)) return false;

        // 7 Set è un'interfaccia, non si può istanziare
		HashSet<T> s = new HashSet<T>();

        // 8 il cast dovrebbe essere a RotatingList<T>
		RotatingList<T> l = (RotatingList) o;

        // 9 perché creare un conflitto di nomi fra elementi di una lista e lista stessa?
		for(T e:l.lista){
			s.add(e);
		}
        // 11 e dopo tutto lo sforzo non funziona nemmeno. 
		for(T e:this.lista){
			if(s.add(e)==true){
				return false;
			}
		}
		return true;
	}

    // 10 niente toString
    public String toString() {
        return lista.toString();
    }
}
