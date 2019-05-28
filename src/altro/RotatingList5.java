package altro;

import java.util.*;
// 1 classe top-level non public
public class  RotatingList<T> {
	private LinkedList<T> list;

	public RotatingList() {
		list = new LinkedList<T>();
	}

	public void add(T elem){
		list.addLast(elem);
	}

	public void rotateLeft(){
		T elem = list.removeFirst();
		list.addLast(elem);
	}

	public void rotateRight(){
		T elem = list.removeLast();
		list.addFirst(elem);
	}

    // 2 annotazione sbagliata "@Overriding"
	@Override
	public boolean equals (Object other){
		if(!(other instanceof RotatingList)){
			return false;
		}

        // 3 cast sbagliato al tipo grezzo
		RotatingList<T> l = (RotatingList) other;
		// 4 equals sbagliato
		for(T elem : l.list){
			if(!(list.contains(elem))){
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString(){
		return list.toString();
	}
}
