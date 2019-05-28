package faella.esercizicreazione;

import java.util.LinkedList;
import java.util.List;
import java.util.Collection;

public class City {

    private String name;
    private List<City> connesse = new LinkedList<>();

    public City(String name) {
        this.name = name;
    }

    public void connect(City other) {
        if (!connesse.contains(other)) {
            connesse.add(other);
            other.connesse.add(this);
        }
    }

    public Collection<City> getConnections() {
        return connesse;
    }

    public String toString() {
        return name;
    }

    public boolean isConnected(City other) {
        LinkedList<City> daVisitare = new LinkedList<>();
        LinkedList<City> visitate = new LinkedList<>();

        for (City c : connesse) {
            // è connessa direttamente
            if (c == other) return true;

            daVisitare.addLast(c);

        }

        City curr;
        while (!daVisitare.isEmpty()) {

            curr = daVisitare.removeFirst();

            for (City c : curr.connesse) {

                if (c == other) return true;

                if(!daVisitare.contains(c) && !visitate.contains(c)) {
                    daVisitare.addLast(c);
                }
            }

            visitate.addLast(curr);
        }

        return false;
    }

    public static void main(String[] args) {
        City napoli = new City("Napoli");
        City roma = new City("Roma");
        City salerno = new City("Salerno");
        City parigi = new City("Parigi");

        napoli.connect(salerno);
        napoli.connect(roma);
        Collection<City> r_conn = roma.getConnections();
        System.out.println(r_conn);
        System.out.println(roma.isConnected(salerno));
        System.out.println(roma.isConnected(parigi));
    }
}
