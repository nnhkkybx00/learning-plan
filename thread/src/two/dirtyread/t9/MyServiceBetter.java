package two.dirtyread.t9;

public class MyServiceBetter {
    public MyOneLIst addServiceMethod(MyOneLIst list,String data){
        try {
            synchronized (list){ /**list参数对象在项目中是一份实例，是单例的，
                        而且也正需要对list参数的getSize()方法做同步的调用，所以就对list参数进行同步处理**/
                if(list.getSize() < 1){ //“同步化”。
                    Thread.sleep(2000);
                    list.add(data);
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return list;
    }
}
