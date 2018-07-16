package two.syn_volatile;

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

            }
        }
        System.out.println("停下来了！！");
    }


    public void stopMethod(){
        isContinueRun = false;
    }
}
