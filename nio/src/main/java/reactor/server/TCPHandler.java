package reactor.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * ━━━━━━如来保佑━━━━━━
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 *
 * @author Ledev4
 * @date 2018/6/20 19:23 星期三
 */


public class TCPHandler implements Runnable {

    private final SelectionKey sk;
    private final SocketChannel sc;

    int state;

    public TCPHandler(SelectionKey sk, SocketChannel sc)
    {
        this.sk = sk;
        this.sc = sc;
        state = 0; // 初始状态设置为READING，client连接后，读取client请求
    }


    public void run() {
        try
        {
            if (state == 0)
                read(); // 读取客户端请求数据
            else
                send(); // 向客户端发送反馈数据

        }
        catch (IOException e)
        {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
        }
    }

    private void closeChannel()
    {
        try
        {
            sk.cancel();
            sc.close();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    private synchronized void read() throws IOException
    {
        // 创建一个读取通道数据的缓冲区
        ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
        inputBuffer.clear();

        int numBytes = sc.read(inputBuffer); // 读取数据
        if (numBytes == -1)
        {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
            return;
        }
        // 将读取的字节转换为字符串类型
        String str = new String(inputBuffer.array());
        if ((str != null) && !str.equals(" "))
        {
            process(str); // 进一步处理获取的数据
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " > " + str);
            // 切换状态，准备向客户端发送反馈数据
            state = 1;
            // 通过key改变通道注册的事件类型
            sk.interestOps(SelectionKey.OP_WRITE);
            sk.selector().wakeup();
        }
    }

    private void send() throws IOException
    {
        String str = "Your message has sent to " + sc.socket().getLocalSocketAddress().toString()
                + "\r\n";
        // 创建发送数据的缓存区并写入数据
        ByteBuffer outputBuffer = ByteBuffer.allocate(1024);

        outputBuffer.put(str.getBytes());
        outputBuffer.flip();
        // 向客户端发送反馈数据
        sc.write(outputBuffer);
        // 切换状态
        state = 0;
        // 通过key改变通道注册的事件类型
        sk.interestOps(SelectionKey.OP_READ);
        sk.selector().wakeup();
    }

    void process(String str)
    {
        // do process(decode, logically process, encode)..
        // 略
    }
}
