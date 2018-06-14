package two.dirtyread;

public class ThreadA extends Thread {
    public PublicVar publicVar;
    public ThreadA(PublicVar publicVar){
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}
