package fourthTask;

public class TestThread implements Runnable {

    private HarrisOrderedList<String> harrisOrderedList;

    public TestThread(HarrisOrderedList<String> harrisOrderedList) {
        this.harrisOrderedList = harrisOrderedList;
    }

    @Override
    public void run() {
        String currThreadName = Thread.currentThread().getName();
        harrisOrderedList.add(currThreadName);

        if (currThreadName.equals("Thread-4")) {
            System.out.println("Remove: " + harrisOrderedList.remove(currThreadName));
        }
    }
}
