package reactor.server;

import java.io.IOException;
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
 * @date 2018/6/20 19:21 星期三
 */


public class Acceptor implements Runnable {
    private Reactor reactor;

    public Acceptor(Reactor reactor)
    {
        this.reactor = reactor;
    }

    public void run()
    {
        try
        {
            // 接受client连接请求
            SocketChannel sc = reactor.serverSocketChannel.accept();
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " is connected.");

            if (sc != null)
            {
                sc.configureBlocking(false);
                // SocketChannel向selector注册一个OP_READ事件，然后返回该通道的key
                SelectionKey sk = sc.register(reactor.selector, SelectionKey.OP_READ);
                // 使一个阻塞住的selector操作立即返回
                reactor.selector.wakeup();
                // 通过key为新的通道绑定一个附加的TCPHandler对象
                sk.attach(new TCPHandler(sk, sc));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
