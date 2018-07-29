package nio_time_server.client;

public class TimeClient {
    public static void main(String[] args) {
        new Thread(new TimeClientHandle("localhost",8080),"TimeClient-001").start();
    }
}
