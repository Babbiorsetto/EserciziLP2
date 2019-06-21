package faella.esercizithread;

public class MetodiStatici {

    public static void executeInParallel(Runnable[] toRun, int k) throws InterruptedException {

        Thread thread;
        int[] inEsecuzione = {0};

        class MyThread extends Thread {

            Runnable r;

            public MyThread(Runnable r) {
                this.r = r;
            }

            @Override
            public void run() {
                r.run();
                synchronized(inEsecuzione) {
                    inEsecuzione[0]--;
                    inEsecuzione.notifyAll();
                }
            }
        };

        for (Runnable r : toRun) {
            thread = new MyThread(r);
            synchronized(inEsecuzione) {
                while(inEsecuzione[0] >= k) {
                    inEsecuzione.wait();
                }
                inEsecuzione[0]++;
                thread.start();
            }
        }

        /*
        public static void main(String[] args) {

            Runnable[] runs = new Runnable[20];
            class MyRunnable implements Runnable {
                private int i;
                public MyRunnable(int i) {
                    this.i = i;
                }
                @Override
                public void run() {
                    System.out.println("This is " + i);
                    try {Thread.sleep(2000);} catch (Exception e) {}
                }
            }

            for (int i = 0; i < runs.length; i++) {
                runs[i] = new MyRunnable(i);
            }

            try {faella.esercizithread.MetodiStatici.executeInParallel(runs, 5);}
            catch (Exception e) {}
        }
        */
    }

    public static void atLeastOne(int n, Runnable r) {

        Thread[] threads = new Thread[n];

        class MyThread extends Thread {

            Runnable r;

            MyThread(Runnable r) {
                this.r = r;
            }

            @Override
            public void run() {
                r.run();
                for (Thread t : threads) {
                    t.interrupt();
                }
            }
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(r);
            threads[i].start();
        }

        /*
        public static void main(String[] args) {

		atLeastOne(15, () -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("Over");
            });
	   }
       */
    }

}
