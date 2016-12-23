/**
 * Created by youssefelabd on 11/17/16.
 */
public class Test {

    public static boolean isPrime(long num) {
        boolean isPrime = true;

        if(num > 2 && num%2 == 0){
            isPrime = false;
            return isPrime;
        }
        int sqrtNum = (int)Math.sqrt(num)+1;
        for (int i = 3; i < sqrtNum; i+=2) {
            if (num % i == 0) {
                //System.out.println("Not Prime: "+num);
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(2));
    }
}
