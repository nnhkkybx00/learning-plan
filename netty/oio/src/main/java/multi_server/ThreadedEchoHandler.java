package multi_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {
    private Socket socket;
    public ThreadedEchoHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            try {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                Scanner scanner = new Scanner(inputStream);
                PrintWriter out = new PrintWriter(outputStream,true);

                out.println("Hello ! Enter BYE to exit.");
                boolean done = false;
                while (!done && scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    out.println("Echo: " + line);
                    if("BYE".equals(line.trim())){
                        done = true;

                    }
                }

            }finally {
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

}
}
