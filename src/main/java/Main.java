import firstTask.Mutex;
import firstTask.TestThread;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FirstTest();
    }

    private static void FirstTest() throws InterruptedException {
        LinkedBlockingQueue<Runnable> waitingThreads = new LinkedBlockingQueue<>();
        Mutex mutex1 = new Mutex(waitingThreads);
        Mutex mutex2 = new Mutex(waitingThreads);

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                new Thread(new TestThread(mutex1)).start();
            } else {
                new Thread(new TestThread(mutex2)).start();
            }
        }

        // Test notify
//        for (int i = 0; i < 10; i++) {
//            Thread.sleep(10000);
//            System.out.println("\n\n\n");
//            mutex1._notify();
//        }

        // Test notifyAll
        mutex1._notifyAll();
    }
}
