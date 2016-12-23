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

        boolean isEven = true;

        int i = (int) ((upper + 1 - lower));
        if (i%numThreads == 0){
            i = i/numThreads;
            System.out.println(i);

        }else {
            i = (i-1)/numThreads;
            isEven = false;
        }
        //System.out.println("intervals are " + i);
        countPrimeThreads = new CountPrimes[numThreads];
        int upperCounter = numThreads-1;
        for (int j = 0; j < numThreads; j++) {
            if (!isEven && j == numThreads - 1){
                countPrimeThreads[j] = new CountPrimes((lower + (j*i)), upper);
            }else if (!isEven){
                countPrimeThreads[j] = new CountPrimes((lower + (j*i)), (lower + ((j+1)*i)-1));
            }else {
                countPrimeThreads[j] = new CountPrimes((lower + (j * i)), upper - (upperCounter * i));
            }

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

        for (CountPrimes initializedThread : countPrimeThreads) {
            try {
                initializedThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    public synchronized static void decrementCountDownLatch() {
        latch.countDown();
    }



}
