package faella.esercizithread;

public class Exchanger<T> {

    private Object lock = new Object();
    private T elem1;
    private T elem2;

    public T exchange(T item) throws InterruptedException {

        boolean primo = false;

        synchronized(lock) {

            if (elem1 == null) {
                primo = true;
                elem1 = item;
            }

            while (elem2 == null && primo) {
                lock.wait();
            }

            if (!primo) {
                if (elem2 == null) {
                    elem2 = item;
                    lock.notify();
                    return elem1;
                } else {
                    throw new UnsupportedOperationException("non puoi usare due volte un Exchanger");
                }
            }
            return elem2;
        }
    }

    public static void main(String[] args) {

        Exchanger<String> exc = new Exchanger<String>();

        class MyThread extends Thread {

        	private String name;
        	private String mine;

        	public MyThread(String name, String x) {
        		this.name = name;
        		mine = x;
        	}

        	@Override
        	public void run() {

                try {
                    mine = exc.exchange(mine);
                } catch (InterruptedException e) {
                	return;
                }

                System.out.println(name + mine);
            }
        }

        Thread t1 = new MyThread("t1", "Casa");
        Thread t2 = new MyThread("t2", "Cane");

        t1.start();

        try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {}

        t2.start();

        Thread t3 = new MyThread("t3", "Cose");
        t3.start();
    }
}
