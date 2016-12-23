import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by youssefelabd on 11/16/16.
 */
public class CountPrimes extends Thread {
    private long lower;
    private long upper;
    private static AtomicInteger numPrimes;

    public CountPrimes(long lower, long upper) {
        this.lower = lower;
        this.upper = upper;
        numPrimes = new AtomicInteger(0);

    }

    public void run() {
        int i = (int) lower;
        for (int j = i; j <= upper; j++) {
            //System.out.println(j);
            if (isPrime(j)) {
                //System.out.println(j + " is Prime");
                numPrimes.incrementAndGet();
                // System.out.println("num primes incremented to " + numPrimes);
            }

        }
        CountPrimesExecutor.decrementCountDownLatch();
    }

    public static int getNumPrimes() {
        return numPrimes.get();

    }

    public static void resetNumPrimes() {
        numPrimes.set(0);;

    }

    public boolean isPrime(long num) {
        if (num <= 1){
            return false;
        }
        if(num > 2 && num%2 == 0){
            return false;
        }
        for (int i = 3; i < Math.sqrt(num)+1; i+=2) {
            if (num % i == 0) {
                //System.out.println("Not Prime: "+num);
                return false;
            }
        }
        return true;
    }
}
