package multi_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadEchoServer {
    public static void main(String[] args) {
        try {
            int i = 1;
            ServerSocket serverSocket = new ServerSocket(8189);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(socket);
                Thread thread = new Thread(r);
                thread.start();
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
