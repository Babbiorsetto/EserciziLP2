package faella.esercizithread;

public class PeriodicExecutor {

    private int[] disponibili = new int[1];

    public PeriodicExecutor(int max) {
        this.disponibili[0] = max;
    }

    public void addTask(Runnable task, long x) {
        new MyThread(task, x).start();
    }

    private class MyThread extends Thread {

        private Runnable task;
        private long x;

        public MyThread(Runnable task, long x) {
            this.task = task;
            this.x = x;
        }

        @Override
        public void run() {
            while (true) {
                synchronized(disponibili) {
                    while(disponibili[0] <= 0) {
                        try{
                            disponibili.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    disponibili[0]--;
                }
                task.run();
                synchronized(disponibili) {
                    disponibili[0]++;
                    disponibili.notifyAll();
                }
                try{
                    sleep(x);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        PeriodicExecutor exe = new PeriodicExecutor(3);

        class MyRunnable implements Runnable {
            private int i;
            public MyRunnable(int i) {
                this.i = i;
            }
            @Override
            public void run() {
                System.out.println("This is " + i);
            }
        }

        for (int i = 1; i < 6; i++) {
            exe.addTask(new MyRunnable(i), i * 3000);
        }
        exe.addTask(new MyRunnable(0), 200);
        System.out.println("fatto!");
    }
}
