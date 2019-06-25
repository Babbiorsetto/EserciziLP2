public class BlockingArray<T> {

    private List<T> array;
    private final int size;

    public BlockingArray(int size) {
        this.size = size;
        array = new ArrayList<T>(size);
    }

    public void put(int index, T toAdd) throws InterruptedException {
            synchronized(array) {
            while (array.get(index) != null) {
                array.wait();
            }
            array.set(index, toAdd);
            array.notifyAll();
        }
    }

    public T take(int index) throws InterruptedException {
        T ret;
        synchronized(array) {
            while (array.get(index) == null) {
                array.wait();
            }
            ret = array.get(index);
            array.notifyAll();
            return ret;
        }
    }

}
