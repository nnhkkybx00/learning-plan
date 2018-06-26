package two.dirtyread.t9;

public class MyThread1 extends Thread {
    private MyOneLIst list;
    public MyThread1(MyOneLIst list){
        super();
        this.list = list;
    }

    @Override
    public void run() {
//        MyService myService = new MyService();
//        myService.addServiceMethod(list,"A");
        MyServiceBetter myServiceBetter = new MyServiceBetter();
        myServiceBetter.addServiceMethod(list,"A");
    }
}
