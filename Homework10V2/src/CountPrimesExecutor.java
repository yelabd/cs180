import java.util.concurrent.CountDownLatch;

/**
 * Created by youssefelabd on 11/16/16.
 */
public class CountPrimesExecutor {
    private long lower;
    private long upper;
    private int numThreads;
    private static CountDownLatch latch;
    private CountPrimes[] countPrimeThreads;

    public CountPrimesExecutor(int numThreads, long lower, long upper) {
        this.numThreads = numThreads;
        this.lower = lower;
        this.upper = upper;
        latch = new CountDownLatch(numThreads);

        int i = (int) ((upper + 1 - lower) / numThreads);
        //System.out.println("intervals are " + i);
        countPrimeThreads = new CountPrimes[numThreads];
        int upperCounter = numThreads-1;
        for (int j = 0; j < numThreads; j++) {
            countPrimeThreads[j] = new CountPrimes((lower + (j*i)), upper - (upperCounter*i));
            //System.out.println("lower: "+(lower + (j*i)));
            //System.out.println("upper: "+(upper - (upperCounter*i)));
            upperCounter--;
        }

    }

    public void executeThreads() {
        for (CountPrimes initializedThread : countPrimeThreads) {
            initializedThread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void decrementCountDownLatch() {
        latch.countDown();
    }


}
