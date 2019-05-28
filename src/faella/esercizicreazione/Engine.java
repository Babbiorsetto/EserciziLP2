package faella.esercizicreazione;

/*La classe Engine rappresenta un motore a combustione, caratterizzato da cilindrata (in cm3) e potenza (in cavalli).
Due oggetti Engine sono uguali se hanno la stessa cilindrata e la stessa potenza.
Il metodo by Volume converte questo Engine in modo che venga confrontata solo la cilindrata.
Analogamente, il metodo byPower converte questo Engine in modo che venga confrontata solo la potenza.
*/

public class Engine {
    protected final int cilindrata;
    protected final int potenza;

    public Engine(int cilindrata, int potenza) {
        this.cilindrata = cilindrata;
        this.potenza = potenza;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Engine) {
            Engine e = (Engine) other;
            return cilindrata == e.cilindrata && potenza == e.potenza;
        }
        return false;
    }

    public Engine byVolume() {

        class ByVolume extends Engine {

            ByVolume(int cilindrata, int potenza) {
                super(cilindrata, potenza);
            }
            @Override
            public boolean equals(Object other) {
                if (other instanceof Engine) {
                    if (other instanceof ByVolume) {
                        ByVolume b = (ByVolume) other;
                        return cilindrata == b.cilindrata;
                    }
                    return super.equals(other);
                }
                return false;
            }
        }

        return new ByVolume(this.cilindrata, this.potenza);
    }

    public Engine byPower() {

        class ByPower extends Engine {

            ByPower(int cilindrata, int potenza) {
                super(cilindrata, potenza);
            }

            @Override
            public boolean equals(Object other) {
                if (other instanceof Engine) {
                    if (other instanceof ByPower) {
                        ByPower b = (ByPower) other;
                        return potenza == b.potenza;
                    }
                    return super.equals(other);
                }
                return false;
            }
        }

        return new ByPower(this.cilindrata, this.potenza);
    }

    public String toString() {
        return "(" + cilindrata + ".0 cm3, " + potenza + ".0 CV)";
    }

    public static void main(String[] args) {

        Engine a = new Engine(1200, 69);
        Engine b = new Engine(1200, 75);
        Engine c = new Engine(1400, 75);

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);

        Engine aVol = a.byVolume();
        Engine bVol = b.byVolume();

        System.out.println("creati aVol e bVol");

        System.out.println("a == aVol: " + (a == aVol));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("aVol.equals(b): " + aVol.equals(b));
        System.out.println("bVol.equals(a): " + bVol.equals(a));
        System.out.println("aVol.equals(bVol): " + aVol.equals(bVol));

        Engine bPow = b.byPower();
        Engine cPow = c.byPower();

        System.out.println("creati bPow e cPow");

        System.out.println("b.equals(c): " + b.equals(c));
        System.out.println("bPow.equals(c): " + bPow.equals(c));
        System.out.println("cPow.equals(b): " + cPow.equals(b));
        System.out.println("bPow.equals(cPow): " + bPow.equals(cPow));
    }
}
