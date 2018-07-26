package two.syn_volatile;

/**
 * 外练互斥，内修可见
 */

public class Service {
    private boolean isContinueRun = true;
    /*
    public void runMethod(){
        while (isContinueRun){

        }
        System.out.println("停下来了！！");
    }
     */
    /**关键字synchronized 可以具有可视性  更改runMethod方法
     *
     * 关键字synchronized 包含互斥性和可见性
     * **/
    public void runMethod(){
        String anyString = new String();
        while (isContinueRun){
            synchronized (anyString){
            /*synchronized 不仅可以解决一个线程看到对象处于不一致的状态，还可以保证进入同步方法或者同步块
            * 的每个线程，都看到由同一个锁保护之前所有的修改效果*/
            }
        }
        System.out.println("停下来了！！");
    }


    public void stopMethod(){
        isContinueRun = false;
    }
}
