package faella.esercizithread;

import java.util.Random;
import java.util.Arrays;

/* X = risolve race condition, E = errore a compilazione, R = exception a runtime
 (a) non aggiungere nulla
 (b) synchronized run()
 (c) 1 = "synchronized(this) {"              4 = "}"
E(d) 1 = "synchronized {"                    4 = "}"
X(e) 1 = "synchronized (a) {"                4 = "}"
X(f) 1 = "synchronized (MyThread.class) {"   4 = "}"
R(g) 1 = "a.wait()"                          4 = "a.notify();"
 (h) 2 = "synchronized (this) {"             3 = "}"
E(i) 2 = "synchronized (a[i]) {"             3 = "}"
X(j) 2 = "synchronized (a) {"                3 = "}"
X(k) 2 = "synchronized (MyThread.class) {"   3 = "}"
*/

public class MissingSynch3 {

    private static int[] a = new int[20];

    private static class MyThread extends Thread {

        @Override
        public void run() {
            final int n = a.length - 1;
            // 1
            for (int i = 0; i <= n/2; i++) {
                // 2
                int temp = a[i];
                a[i] = a[n-i];
                a[n-i] = temp;
                // 3

                // inserito per far emergere la race condition
                try {
                    sleep(2);
                } catch (InterruptedException e) {}
            }
            // 4
        }
    }

    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(50);
        }

        System.out.println(Arrays.toString(a));

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

        System.out.println(Arrays.toString(a));

    }

}
