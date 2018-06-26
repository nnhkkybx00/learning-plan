package two.dirtyread.t9;

public class MyService {
    public MyOneLIst addServiceMethod(MyOneLIst list,String data){
        try {
            if(list.getSize() < 1){ //会出现脏读，出现的原因是两个线程以异步的方式返回list参数的size()大小，解决方法就是“同步化”。
                Thread.sleep(2000);
                list.add(data);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return list;
    }
}
