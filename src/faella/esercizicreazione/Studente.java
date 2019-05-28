package faella.esercizicreazione;

/*Implementare la classe Studente e le due sottoclassi Triennale e Magistrale. Uno studente è
caratterizzato da nome e matricola. Ciascuna sottoclasse ha un prefisso che viene aggiunto a
tutte le sue matricole. Due studenti sono considerati uguali da equals se hanno lo stesso nome e
la stessa matricola (compreso il prefisso).

true
false
Anna N86/004312
*/

public class Studente {

    protected String nome;
    protected String matricola;

    protected Studente(String nome, String matricola) {
        this.nome = nome;
        this.matricola = matricola;
    }


    static class Triennale extends Studente {
        private static String prefisso;

        public Triennale(String nome, String matricola) {
            super(nome, matricola);
        }

        public static void setPrefisso(String prefisso) {
            Triennale.prefisso = prefisso;
        }

        public String toString() {
            return prefisso + "/" + matricola;
        }
    }

    static class Magistrale extends Studente {
        private static String prefisso;

        public Magistrale(String nome, String matricola) {
            super(nome, matricola);
        }

        public static void setPrefisso(String prefisso) {
            Magistrale.prefisso = prefisso;
        }

        public String toString() {
            return prefisso + "/" + matricola;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Studente)) {
            return false;
        }

        Studente altro = (Studente) other;
        if (getClass() == altro.getClass() && nome.equals(altro.nome) && matricola.equals(altro.matricola)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Studente.Triennale.setPrefisso ("N86");
        Studente.Magistrale.setPrefisso ("N97");
        Object luca1 = new Studente.Triennale("Luca", "004312"),
        luca2 = new Studente.Triennale("Luca", "004312"),
        anna1 = new Studente.Triennale("Anna", "004312"),
        anna2 = new Studente.Magistrale("Anna", "004312");
        System.out.println(luca1.equals(luca2));
        System.out.println(anna1.equals(anna2));
        System.out.println(anna1);
    }
}
