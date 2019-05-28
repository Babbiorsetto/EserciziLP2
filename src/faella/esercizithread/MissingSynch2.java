package faella.esercizithread;

import java.util.Random;
import java.util.Arrays;

/* X = risolve race condition, E = errore a compilazione, R = exception a runtime
 (a) non aggiungere nulla
 (b) 1 = "synchronized(this) {"              4 = "}"
E(c) 1 = "synchronized {"                    4 = "}"
X(d) 1 = "synchronized (a) {"                4 = "}"
 (e) 2 = "synchronized (this) {"             3 = "}"
E(f) 2 = "synchronized (a[i]) {"             3 = "}"
X(g) 2 = "synchronized (b) {"                3 = "}"
E(h) 2 = "a.wait();"                         3 = "a.notify();"
*/

public class MissingSynch2 {

    private static int[] a = new int[20];
    private static int[] b = new int[20];

    private static class MyThread extends Thread {

        @Override
        public void run() {
            // 1
            for (int i = 0; i < a.length; i++) {
                // 2
                if (a[i] > 0) {

                    // inserito per far emergere la race condition
                    try {sleep(2);} catch (InterruptedException e) {}

                    b[i] = a[i];
                    a[i] = 0;
                }
                // 3

            }
            // 4
        }
    }

    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(50) - 10;
        }

        System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(b));

        Thread t1, t2;
        t1 = new MyThread();
        t2 = new MyThread();

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {

        }

        // System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

    }

}
