/**
 * Created by youssefelabd on 11/16/16.
 */
public class CountPrimes extends Thread {
    private long lower;
    private long upper;
    private static int numPrimes = 0;

    public CountPrimes(long lower, long upper) {
        this.lower = lower;
        this.upper = upper;

    }

    public void run() {
        int i = (int) lower;
        for (int j = i; j <= upper; j++) {
            //System.out.println(j);
            if (isPrime(j) && j != 1 && j != 0) {
                //System.out.println(j + " is Prime");
                numPrimes++;
                // System.out.println("num primes incremented to " + numPrimes);
            }
        }
        CountPrimesExecutor.decrementCountDownLatch();


    }

    public static int getNumPrimes() {
        return numPrimes;

    }

    public static void resetNumPrimes() {
        numPrimes = 0;

    }

    public boolean isPrime(long num) {
        boolean isPrime = true;

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                //System.out.println("Not Prime: "+num);
                isPrime = false;
                break;
            }else {
                //System.out.println("Prime: "+num);
            }
        }
        return isPrime;
    }
}
