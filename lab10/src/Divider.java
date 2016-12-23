import com.sun.org.apache.xpath.internal.operations.Div;

/**
 * Created by youssefelabd on 11/15/16.
 */
public class Divider implements Runnable {
    private static int counter = 0;
    private int start;
    private int end;

    public Divider(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end;i++){
            if (i%7 == 0){
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        Runnable thread1 = new Divider(1,1000);
        Runnable thread2 = new Divider(1001,2000);
        Runnable thread3 = new Divider(2001,3000);

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        Thread t3 = new Thread(thread3);

        t1.start();
        t2.start();
        t3.start();

       try {
           t1.join();
           t2.join();
           t3.join();
       }catch (InterruptedException e){
           System.out.println("Big Problem");
       }

       System.out.println(counter);


    }
}
