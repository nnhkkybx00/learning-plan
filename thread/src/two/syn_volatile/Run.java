package two.syn_volatile;

public class Run {
     public static void main(String[] args){
        try {
            Service service = new Service();
            ThreadA threadA = new ThreadA(service);
            threadA.start();
            Thread.sleep(500);
            ThreadB threadB = new ThreadB(service);
            threadB.start();
            System.out.println("已经发起停止指令！！");
            /**出现死循环，是因为各线程间的数据没有可视性造成的，
             关键字synchronized 可以具有可视性**/
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
