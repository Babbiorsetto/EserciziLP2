package faella.esercizicreazione;

import faella.accessori.*;

public class Container<T extends Employee> {
    public<E extends Integer> Container(E arg) {

    }
    public<E extends String> Container(E arg) {

    }

    public static void main(String[] args) {

        // Le ultime due istruzioni devono provocare errore
        Container<Employee> cont1 = new <String>Container<Employee>("Ciao");
        Container<Employee> cont2 = new <Integer>Container<Employee>(new Integer(42));
        Container<Manager> cont3 = new <Integer>Container<Manager>(new Integer(42));
        // Container<Employee> cont4 = new <Object>Container<Employee>(new Object());
        // Container<Person> cont5 = new <Integer>Container<Person>(new Integer(42));


    }
}
