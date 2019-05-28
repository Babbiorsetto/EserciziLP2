package altro;

import java.util.*;

public class RotatingList<T> extends ArrayList<T> {

  // 5 public?
  public HashSet<T> elements;

  public RotatingList() {
    super();
    elements = new HashSet<T>();
  }

  @Override
  public boolean add(T elem) {
    elements.add(elem);
    return super.add(elem);
    // 3 non ritorna un boolean. Non ritorna niente...
  }

  @Override
  public boolean equals(Object other) {
      // 4 controllo ridondante
    if(!(other != null && other instanceof RotatingList)) return false;
    // 1 RotatingList grezza non permette di tirare fuori T
    RotatingList<T> list2 = (RotatingList)other;
    for(T elem : this.elements) {
      if(!list2.elements.contains(elem)) return false;
    }
    for(T elem : list2.elements) {
      if(!this.elements.contains(elem)) return false;
    }
    return true;
  }

  // 2 remove da solo problemi.
  /*@Override
  public void remove(T elem) {
    super.remove(elem);
    elements.remove(elem);
}*/

  public void rotateLeft() {
    T tempHead = this.get(0);
    for(int i = 1; i < this.size(); i++) {
      this.set(i-1, this.get(i));
    }
    this.set(this.size()-1, tempHead);
  }

  public void rotateRight() {
    T tempTail = this.get(this.size()-1);
    for(int i = this.size()-2; i>=0; i--) {
      this.set(i+1, this.get(i));
    }
    this.set(0, tempTail);
  }
}
