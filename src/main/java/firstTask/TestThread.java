package firstTask;

public class TestThread implements Runnable {

    private Mutex mutex;

    public TestThread(Mutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            mutex._lock();
            mutex._wait();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        } finally {
            mutex._unlock();
        }
    }
}
