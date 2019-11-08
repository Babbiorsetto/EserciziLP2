package faella.esercizicreazione;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alessandro Rubino
 *
 * 
 */
public class Cellphone {

    /**
     * Stores the cost per minute from each carrier to every other.
     */
    private static Map<String, Map<String, Double>> costs = new HashMap<String, Map<String, Double>>();
    private String name;
    private String number;

    public Cellphone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Sets the cost for a minute of call from caller to receiver in euros.
     * 
     * @param caller The operator of the cellphone making the call
     * @param receiver The operator of the cellphone receiving the call
     * @param cost The cost per minute of a call from caller to receiver
     */
    public static void setCost(String caller, String receiver, double cost) {
        Map<String,Double> myMap = costs.get(caller);
        if (myMap == null) {
            costs.put(caller, new HashMap<String, Double>());
            myMap = costs.get(caller);
        }
        myMap.put(receiver, cost);
    }

    /**
     * Calculates the cost of a call from this to receiver carrier.
     * 
     * @param receiver The operator of the cellphone receiving the call
     * @param duration The duration in minutes
     * @return The cost of the call in euros
     */
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
