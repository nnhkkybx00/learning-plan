import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) throws Exception{

        try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13)){//java7新特性之Try-with-resources statement  执行完try会自动关闭资源
            InputStream in = s.getInputStream();
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}
