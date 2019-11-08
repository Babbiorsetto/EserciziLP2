package faella.esercizithread;

import java.util.HashMap;
import java.util.Map;

public class ForgetfulSet<T> {

    private long delay;
    private Map<T,Long> container = new HashMap<T,Long>();

    public ForgetfulSet(long delay) {
        this.delay = delay;
    }

    public void add(T toAdd) {
        synchronized(container) {
            container.put(toAdd, System.currentTimeMillis());
        }
    }

    public boolean contains(Object o) {
        Long before;
        synchronized(container) {
            before = container.get(o);
            if (before != null) {
                if (System.currentTimeMillis() - before > delay) {
                    container.remove(o);
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForgetfulSet <String> s = new ForgetfulSet<String>(1000);
        s.add("uno");
        s.add("due");
        System.out.println(s.contains("uno") + ", " + s.contains("due") + ", " + s.contains("tre"));
        Thread.sleep(800);
        s.add("tre");
        System.out.println(s.contains("uno") + ", " + s.contains("due") + ", " + s.contains("tre"));
        Thread.sleep(800);
        System.out.println(s.contains("uno") + ", " + s.contains("due") + ", " + s.contains("tre"));
    }
}
