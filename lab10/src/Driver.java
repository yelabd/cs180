/**
 * Created by youssefelabd on 11/15/16.
 */
public class Driver extends Thread {
    static A test = new A();
    static B testB = new B();

    public static void main(String[] args) {
        Thread t = new A();
        t.start();
        Runnable r = new B();
        Thread t2 = new Thread(r);
        t.start();

    }

}
