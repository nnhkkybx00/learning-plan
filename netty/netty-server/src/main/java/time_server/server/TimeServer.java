package time_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 采用线程池和任务队列可以实现一种叫做伪异步的I/O 通信框架
 *
 * 线程池可以设置消息队列的大小和最大线程数，因此，它的资源占用是可控的，无论多少个客户端并发访问，都不会导致资源的耗尽和宕机
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                port = 8080;
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket = server.accept();
                System.out.println("InetAddress" + socket.getInetAddress());
                singleExecutor.execute(new TimeServerHandler(socket));//将请求Socket封装成一个task，然后调用线程池的execute方法执行，
                // 从而避免了每个请求接入都创建一个新的线程
            }
        }finally {
            if(server != null){
                System.out.println("The time server close");
                server.close();
                server = null;//????
            }
        }
    }


}
