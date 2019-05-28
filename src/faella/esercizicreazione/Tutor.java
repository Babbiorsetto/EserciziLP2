package faella.esercizicreazione;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Tutor {

    private List<Detector> detectors = new ArrayList<Detector>();

    public Detector addDetector(int chilometro) {
        Detector nuovo = new Detector(chilometro);
        detectors.add(nuovo);
        return nuovo;
    }

    public class Detector {

        private int chilometro;
        private HashMap<String,Integer> macchine = new HashMap<String,Integer>();

        private Detector(int chilometro) {
            this.chilometro = chilometro;
        }

        public int carPasses(String targa, int tempo) {

            // aggiunge la macchina alla collezione di macchine di questo detector
            macchine.put(targa, tempo);

            // trova l'indice di questo nei Detector assegnati a questo Tutor
            int thisIndex = 0;
            for (int i = 0; i < detectors.size(); i++) {
                if (detectors.get(i) == this) {
                    thisIndex = i;
                    break;
                }
            }

            // terra' il piu' vicino Detector prima di questo che ha visto la stessa macchina
            Detector prev = null;
            // scorre i detectors precedenti a questo scendendo
            for (int i = thisIndex - 1; i >= 0; i--) {
                // il primo la cui collezione di macchine contiene la targa viene salvato
                if (detectors.get(i).macchine.containsKey(targa)) {
                    prev = detectors.get(i);
                    break;
                }
            }

            // se e' stato trovato detector precedente che conteneva la targa
            if (prev != null) {
                // velocita' = spazio percorso / tempo impiegato(in ore)
                return (int) ((this.chilometro - prev.chilometro) / ((tempo - prev.macchine.get(targa)) / 3600.0));
            } else {
                return -1;
            }

        }

    }

    public static void main(String[] args) {
        Tutor tang = new Tutor();
        Tutor.Detector a = tang.addDetector(2);
        Tutor.Detector b = tang.addDetector(5);
        Tutor.Detector c = tang.addDetector(9);
        System.out.println(a.carPasses("NA12345", 0));
        System.out.println(b.carPasses("NA12345", 1200));
        System.out.println(b.carPasses("SA00001", 1200));
        System.out.println(c.carPasses("NA12345", 1320));
        System.out.println(c.carPasses("SA00001", 1380));
    }
}
