package two.dirtyread;

public class PublicVar {
    public String name = "A";
    public String password = "A";
    synchronized public void setValue(String name,String password){
        try{
            this.name = name;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = "+ Thread.currentThread().getName() +
            " name = " + name + " password = " + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

//    public void getValue(){
   synchronized public void getValue(){
        System.out.println("getValue method thread name = "+ Thread.currentThread().getName() +
                " name = " + name + " password = " + password);
    }
}
