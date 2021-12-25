import firstTask.Mutex;
import firstTask.TestThread;
import fourthTask.HarrisOrderedList;
import secondTask.SkipList;
import thirdTask.MichaelAndScottQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        firstTest();
//        secondTest();
//        thirdTest();
        fourthTest();
    }

    private static void firstTest() throws InterruptedException {
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

//        for (int i = 0; i < 10; i++) {
//            Thread.sleep(10000);
//            System.out.println("\n\n\n");
//            mutex1._notify();
//        }

        mutex1._notifyAll();
    }

    private static void secondTest() throws InterruptedException {
        SkipList<String> skipList = new SkipList<>(16, 0.5);
        Thread[] threads = new Thread[10];

//        threads[0] = new Thread(new secondTask.TestThread(skipList));
//        threads[0].start();
//        threads[0].join();
//
//        threads[1] = new Thread(new secondTask.TestThread(skipList));
//        threads[1].start();
//        threads[1].join();
//
//        threads[2] = new Thread(new secondTask.TestThread(skipList));
//        threads[2].start();
//        threads[2].join();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new secondTask.TestThread(skipList));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("Contains: " + skipList.contains("Thread-1"));
        skipList.nonSafePrint();
    }

    private static void thirdTest() throws InterruptedException {
        MichaelAndScottQueue<String> michaelAndScottQueue = new MichaelAndScottQueue<>();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new thirdTask.TestThread(michaelAndScottQueue));
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        michaelAndScottQueue.nonSafePrint();
    }

    private static void fourthTest() throws InterruptedException {
        HarrisOrderedList<String> harrisOrderedList = new HarrisOrderedList<>();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new fourthTask.TestThread(harrisOrderedList));
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Contains: " + harrisOrderedList.contains("Thread-1"));
        harrisOrderedList.nonSafePrint();
    }
}
