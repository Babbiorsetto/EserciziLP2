package faella.esercizithread;

public class Alarm extends Thread {

    private final long timeout;
    private boolean anomaly;

    public Alarm(int timeout) {
        this.timeout = timeout * 1000;
        start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized(this) {
                while (!anomaly) {
                    try {
                        this.wait();
                    } catch (InterruptedException e){}
                }
            }
            try {
                sleep(timeout);
                System.out.println("Allarme!");
                anomaly = false;
            } catch (InterruptedException e) {
                anomaly = false;
            }
        }
    }

    public void anomalyStart() {
        synchronized(this) {
            anomaly = true;
            this.notify();
        }
    }

    public void anomalyEnd() {
        this.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Alarm al = new Alarm(2);

        al.anomalyStart();
        Thread.sleep(1000);
        al.anomalyEnd();

		Thread.sleep(50);

        al.anomalyStart();
        Thread.sleep(2050);
        al.anomalyEnd();

        Thread.sleep(50);

        al.anomalyStart();
        Thread.sleep(20);
        al.anomalyStart();

        System.out.println("fine");

    }
}
