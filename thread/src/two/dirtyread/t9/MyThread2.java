package two.dirtyread.t9;

public class MyThread2 extends Thread{

    private MyOneLIst list;
    public MyThread2(MyOneLIst list){
        super();
        this.list = list;
    }

    @Override
    public void run() {
//        MyService myService = new MyService();
//        myService.addServiceMethod(list,"B");
        MyServiceBetter myServiceBetter = new MyServiceBetter();
        myServiceBetter.addServiceMethod(list,"B");
    }
}
