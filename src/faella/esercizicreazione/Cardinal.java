package faella.esercizicreazione;

/*Rappresenta le 16 direzioni della rosa dei venti.*/
public enum Cardinal {
    N(0),
    NNE(1),
    NE(2),
    ENE(3),
    E(4),
    ESE(5),
    SE(6),
    SSE(7),
    S(8),
    SSW(9),
    SW(10),
    WSW(11),
    W(12),
    WNW(13),
    NW(14),
    NNW(15);

    // i punti hanno un indice assegnato per permettere le funzionalita'
    // dopo una riflessione ho deciso di non usare ordinal() perche' potrebbe cambiare se l'ordine delle costanti cambiasse.
    private int index;

    private Cardinal(int index) {
        this.index = index;
    }

    /*prende come argomento un punto cardinale x e restituisce vero
    * se questo punto cardinale è diametralmente opposto ad x
    */
    public boolean isOpposite(Cardinal other) {
        // il punto opposto ha indice che differisce di 8 da questo
        return Math.abs(this.index - other.index) == 8;
    }

    /*prende come argomento due punti cardinali, non opposti,
    * e restituisce il punto cardinale intermedio tra i due.
    * Se non c'e' esattamente un punto medio, quello piu' vicino in senso antiorario.
    */
    public static Cardinal mix(Cardinal primo, Cardinal secondo) {
        Cardinal toReturn = null;

        //
        if (primo.isOpposite(secondo)) {
            throw new IllegalArgumentException("I due punti non possono essere opposti");
        }

        // calcola l'indice medio
        int resultIndex = (primo.index + secondo.index) / 2;
        // per costruzione, se gli indici differiscono per piu' di 8,
        // quello giusto e' l'opposto di quello calcolato
        if (Math.abs(primo.index - secondo.index) > 8 ) {
            resultIndex = oppositeIndex(resultIndex);
        }

        for (Cardinal c : Cardinal.values()) {
            if (c.index == resultIndex) {
                toReturn  = c;
            }
        }
        return toReturn;
    }

    /*
    * Ritorna l'indice del punto opposto a quello dell'indice argomento
    */
    private static int oppositeIndex(int index) {
        // se questo ha indice 0-7, gli opposti sono 8-15
        // se questo 8-15, gli opposti 0-7
        return index < 8 ? index + 8 : index - 8;
    }

    public static void main(String[] args) {
        Cardinal nord = Cardinal.N;
        System.out.println(nord.isOpposite(Cardinal.S));
        Cardinal nordest = Cardinal.mix(Cardinal.N, Cardinal.E);
        assert nordest==Cardinal.NE : "Errore inaspettato!";
        Cardinal nordnordest = Cardinal.mix(nordest, Cardinal.N);
        System.out.println(nordnordest);
    }
}
