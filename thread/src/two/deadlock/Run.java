package two.deadlock;

public class Run {
    public static void main(String[] args) throws InterruptedException{
        DealThread t1 = new DealThread();
        t1.setFlag("a");
        Thread thread = new Thread(t1);
        thread.start();
        Thread.sleep(100);
        Thread thread1 = new Thread(t1);
        t1.setFlag("b");
        thread1.start();
    }
}
