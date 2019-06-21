package faella.esercizithread;

public class SharedCounter {

    private int[] counter = new int[1];

    public SharedCounter() {
        counter[0] = 0;
    }

    public void incr() {
        synchronized(counter) {
            counter[0]++;
            counter.notifyAll();
        }
    }

    public void decr() {
        synchronized(counter) {
            if(counter[0] > 0) {
                counter[0]--;
                counter.notifyAll();
            }
        }
    }

    public void waitForValue(int n) throws InterruptedException {
        synchronized(counter) {
            while(counter[0] != n) {
                counter.wait();
            }
        }
    }

    public static void main(String[] args) {

        SharedCounter sc = new SharedCounter();

        class MyThread extends Thread {
            @Override
            public void run() {
                while(true){
                    sc.incr();
                    try{
                        sleep(3000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

        class MyThread2 extends Thread {
            @Override
            public void run() {
                while(true) {
                    sc.decr();
                    try{
                        sleep(3000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

        Thread a = new MyThread();
        Thread b = new MyThread();
        Thread c = new MyThread();
        Thread d = new MyThread2();
        a.start();b.start();c.start();d.start();
        try {
            sc.waitForValue(15);
        } catch (InterruptedException e) {}
        System.out.println("done!");
        a.interrupt();
        b.interrupt();
        c.interrupt();
        d.interrupt();

    }

}
