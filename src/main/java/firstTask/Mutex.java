package firstTask;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Mutex {

    private AtomicReference<Runnable> currentThread = new AtomicReference<>();
    private LinkedBlockingQueue<Runnable> waitingThreads;

    public Mutex(LinkedBlockingQueue<Runnable> waitingThreads) {
        this.waitingThreads = waitingThreads;
    }

    public void _lock() throws InterruptedException {
        if (Thread.currentThread().equals(currentThread.get())) {
            throw new RuntimeException("You can't lock mutex 2 or more times");
        }

        while (!currentThread.compareAndSet(null, Thread.currentThread())) {
            Thread.yield();
        }
        System.out.println("Mutex was locked by: " + Thread.currentThread().getName());
    }

    public void _unlock() {
        if (!Thread.currentThread().equals(currentThread.get())) {
            throw new RuntimeException("You can't call unlock when you don't have lock");
        }

        System.out.println("Mutex was unlocked by: " + Thread.currentThread().getName());
        currentThread.set(null);
    }

    public void _wait() throws InterruptedException {
        Thread thread = Thread.currentThread();
        if (!thread.equals(currentThread.get())) {
            throw new RuntimeException("You should lock mutex before use of wait method");
        }

        waitingThreads.put(thread);
        System.out.println(Thread.currentThread().getName() + " - is waiting!");
        _unlock();

        while (waitingThreads.contains(thread)) {
            Thread.yield();
        }

        _lock();
        System.out.println(Thread.currentThread().getName() + " - has finished waiting!");
    }

    public void _notify() throws InterruptedException {
        waitingThreads.take();
        System.out.println(Thread.currentThread().getName() + " has notified!");
    }

    public void _notifyAll() {
        waitingThreads.clear();
        System.out.println(Thread.currentThread().getName() + " has notified all!");
    }
}
