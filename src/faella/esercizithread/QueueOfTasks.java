package faella.esercizithread;

public class QueueOfTasks {

    private Thread last;

    static class MyThread extends Thread {

        private Runnable r;
        private Thread previous;

        public MyThread(Runnable r, Thread previous) {
            this.r = r;
            this.previous = previous;
        }

        @Override
        public void run() {
            if (previous != null) {
                try{previous.join();} catch (InterruptedException e) {return;}
            }
            r.run();
        }
    }

    public void add(Runnable r) {

        Thread t = new MyThread(r, last);
        last = t;
        t.start();

    }

    public static void main(String[] args) {

        QueueOfTasks q = new QueueOfTasks();

        class MyRunnable implements Runnable {
            private int i;
            public MyRunnable(int i) {
                this.i = i;
            }
            @Override
            public void run() {
                try {Thread.sleep(400);} catch (Exception e) {}
                System.out.println("This is " + i);
                try {Thread.sleep(400);} catch (Exception e) {}
            }
        }

        for (int i = 0; i < 20; i++) {
            q.add(new MyRunnable(i));
        }
        System.out.println("fatto!");
    }
}
