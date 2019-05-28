package faella.esercizithread;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class VoteBox extends Thread {

    private int votanti;
    private int votiRicevuti;
    private boolean result;
    private Map<Thread, Boolean> votes = new HashMap<>();

    public VoteBox(int votanti) {
        this.votanti = votanti;
        start();
    }

    @Override
    public void run() {
        synchronized (votes) {
            while (votiRicevuti < votanti) {
                try {votes.wait();} catch (InterruptedException e) {return;}
            }

            int falseAcc = 0;
            int trueAcc = 0;
            for (Boolean v : votes.values()) {
                if (Boolean.TRUE.equals(v)) {
                    trueAcc++;
                } else {
                    assert (Boolean.FALSE.equals(v));
                    falseAcc++;
                }
            }
            result = falseAcc < trueAcc;
            // remove this
            for (Thread t : votes.keySet()) {
                System.out.println(t + "voto ricevuto: " + votes.get(t));
            }
            System.out.println("");
            //
            votes.notifyAll();
        }
    }

    public void vote(boolean value) {
        if (votiRicevuti < votanti) {
            synchronized (votes) {
                if (votiRicevuti < votanti) {
                    if (!votes.containsKey(currentThread())) {
                        votes.put(currentThread(), value);
                        votiRicevuti++;
                        votes.notifyAll();
                    } else {
                        throw new UnsupportedOperationException("Hai già votato");
                    }
                }
            }
        }
    }

    public boolean waitForResult() throws InterruptedException {
        synchronized (votes) {
            while (votiRicevuti < votanti) {
                votes.wait();
            }
            return result;
        }
    }

    public boolean isDone() {
        synchronized (votes) {
            return votiRicevuti < votanti;
        }
    }

    public static void main(String[] args) {

        final Random rand = new Random();
        final int n_thread = 10;
        final int voti_accettati = 6;
		final int tSleep = 200;

		Thread[] threads = new Thread[n_thread];
		VoteBox vb = new VoteBox(voti_accettati);

		class MyThread extends Thread {

			public MyThread(String name) {
				super(name);
			}

			@Override
			public void run() {
				boolean vote = rand.nextBoolean();
				try {
					sleep(tSleep);
                    vb.vote(vote);
				} catch (InterruptedException e) {
                    return;
				}
				System.out.println(currentThread() + " voted: " + vote);
			}

		}

		for (int i = 0; i < n_thread; i++) {
			threads[i] = new MyThread(i + "");
			threads[i].start();
		}

        boolean result = false;
        boolean vote = rand.nextBoolean();
		vb.vote(vote);
        System.out.println("Main voted: " + vote);
        try{result = vb.waitForResult();} catch (InterruptedException e) {}
		System.out.println("Risultato: " + result);
    }

}
