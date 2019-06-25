package faella.esercizithread;

import java.util.HashMap;
import java.util.Map;

public class Cellphone {

    private static Map<String, Map<String, Double>> costs = new HashMap<String, Map<String, Double>>();
    private String name;
    private String number;

    public Cellphone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public static void setCost(String caller, String receiver, double cost) {
        Map<String,Double> myMap = costs.get(caller);
        if (myMap == null) {
            costs.put(caller, new HashMap<String, Double>());
            myMap = costs.get(caller);
        }
        myMap.put(receiver, cost);
    }

    public double getCost(Cellphone receiver, double duration) {
        Map<String,Double> myMap = costs.get(this.name);
        if (myMap == null) {
            throw new IllegalArgumentException("Il gestore del chiamante non ha alcuna tariffa associata");
        }
        Double costPerMinute = myMap.get(receiver.name);
        if (costPerMinute == null) {
            throw new IllegalArgumentException("Il gestore del chiamante non ha tariffe associate per il ricevente");
        }
        return duration * costPerMinute;
    }

    public static void main(String[] args) {

    	Cellphone a = new Cellphone("TIMMY", "3341234");
    	Cellphone b = new Cellphone("Megafon", "3355678");
    	Cellphone c = new Cellphone("Odissey", "3384343");
    	Cellphone.setCost("TIMMY", "TIMMY", 0.05);
    	Cellphone.setCost("TIMMY", "Megafon", 0.15);
    	Cellphone.setCost("Megafon", "TIMMY", 0.25);

    	System.out.println(a.getCost(b, 10));
    	System.out.println(b.getCost(a, 8));
    	System.out.println(a.getCost(c, 10));

	}

}
