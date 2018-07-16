package two.syn_volatile;

public class ThreadB extends Thread {
    private Service service;
        public ThreadB(Service service){
            this.service = service;
        }

    @Override
    public void run() {
        super.run();
        service.stopMethod();
    }
}
