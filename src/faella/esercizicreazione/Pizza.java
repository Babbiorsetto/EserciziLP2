package faella.esercizicreazione;

import java.util.*;

public class Pizza implements Comparable<Pizza>, Cloneable {

    private List<Ingrediente> ingredienti = new LinkedList<>();

    public enum Ingrediente {
        POMODORO(20),
        MOZZARELLA(100),
        AGLIO(10),
        SALSICCIA(150),
        FRIARIELLI(80);

        int calorie;

        private Ingrediente(int calorie) {
            this.calorie = calorie;
        }

        public int getCalorie() {
            return calorie;
        }
    }

    public void addIngrediente(Ingrediente ing) {
        ingredienti.add(ing);
    }

    private int getCalorie() {
        int somma = 0;
        for (Ingrediente i : ingredienti) {
            somma += i.getCalorie();
        }
        return somma;
    }

    public int compareTo(Pizza other) {
        int differenza = this.getCalorie() - other.getCalorie();
        if (differenza < 0) {
            return -1;
        } else if(differenza > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public Pizza clone() {

        Pizza nuovo = null;
        try {
            nuovo = (Pizza) super.clone();
            nuovo.ingredienti = new LinkedList<Ingrediente>();
            for (Ingrediente i : ingredienti) {
                nuovo.ingredienti.add(i);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return nuovo;
    }

    public static void main(String[] args) {
        Pizza margherita = new Pizza(), marinara = new Pizza();
        margherita.addIngrediente(Pizza.Ingrediente.POMODORO);
        margherita.addIngrediente(Pizza.Ingrediente.MOZZARELLA);
        marinara.addIngrediente(Pizza.Ingrediente.POMODORO);
        marinara.addIngrediente(Pizza.Ingrediente.AGLIO);
        Pizza sasiccia = new Pizza();
        sasiccia.addIngrediente(Pizza.Ingrediente.SALSICCIA);
        sasiccia.addIngrediente(Pizza.Ingrediente.FRIARIELLI);
        Pizza clonizza = margherita.clone();

        System.out.println( sasiccia.compareTo(margherita));
        System.out.println( marinara.compareTo(margherita));
        System.out.println( clonizza.compareTo(margherita));
    }
}
