package faella.esercizicreazione;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;



public class A1 {

    public static void main(String[] args) {

        List<Integer> l = new LinkedList<Integer>();
        l.add(3);
        l.add(70);
        l.add(23);
        l.add(50);
        l.add(5);
        l.add(20);
        Collections.sort( l, new B1());
        for (Integer i : l ) {
            System.out.println(i);
        }
    }
}

class B1 implements Comparator<Integer> {
    @Override
    public int compare(Integer primo, Integer secondo) {
        if (primo % 2 == secondo % 2) {
            return primo - secondo;
        } else if (primo % 2 == 1) {
            return 1;
        } else {
            return -1;
        }
    }
}
