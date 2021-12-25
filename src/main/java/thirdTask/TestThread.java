package thirdTask;

public class TestThread implements Runnable {

    private MichaelAndScottQueue<String> michaelAndScottQueue;

    public TestThread(MichaelAndScottQueue<String> michaelAndScottQueue) {
        this.michaelAndScottQueue = michaelAndScottQueue;
    }

    @Override
    public void run() {
        michaelAndScottQueue.add(Thread.currentThread().getName());
        if (Math.random() > 0.3) {
            michaelAndScottQueue.remove();
        }
    }
}