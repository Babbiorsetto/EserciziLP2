package faella.esercizicreazione;

import java.util.List;
import java.util.LinkedList;

public final class Volo {

    private static List<Volo> voli = new LinkedList<Volo>();
    private int decollo;
    private int atterraggio;
    private List<Passeggero> passeggeri = new LinkedList<Passeggero>();

    public Volo(int decollo, int atterraggio) {
        this.decollo = decollo;
        this.atterraggio = atterraggio;
        voli.add(this);
    }

    public void add(Passeggero passeggero) {
        for (Volo v : voli) {
            if (siAccavalla(v)) {
                for (Passeggero p : v.passeggeri) {
                    if (p.equals(passeggero)) {
                        throw new IllegalArgumentException("Quel passeggero e' impegnato");
                    }
                }
            }
        }
        passeggeri.add(passeggero);
    }

    private boolean siAccavalla(Volo v) {
        // se uno degli estremi di un volo e' compreso fra gli estremi dell'altro, si accavallano
        return (v.decollo > decollo && v.decollo < atterraggio) ||
                (decollo > v.decollo && decollo < v.atterraggio);
    }

    public static void main(String[] args) {
        Volo v1 = new Volo(1000, 2000);
        Volo v2 = new Volo(1500, 3500);
        Volo v3 = new Volo(3000, 5000);
        Passeggero mario = new Passeggero("Mario");
        Passeggero luigi = new Passeggero("Luigi");
        v1.add(mario);
        v1.add(luigi);
        v3.add(mario);
        // La seguente istruzione provoca l ' eccezione
        v2.add(mario);
    }
}
