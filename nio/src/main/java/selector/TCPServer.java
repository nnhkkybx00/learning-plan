package selector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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
 * @date 2018/6/20 17:44 星期三
 */


public class TCPServer {

    //超时时间，单位毫秒
    private static final int TimeOut = 3000;
    //本地监听端口
    private static final int ListenPort = 1978;

    public static void main(String[] args) throws Exception{
        //创建选择器
        Selector selector = Selector.open();
        //打开监听信道
    /** 只注册了一个 Channel
     *  ServerSocketChannel listenChannel = ServerSocketChannel.open();
        //与本地端口绑定
        listenChannel.socket().bind(new InetSocketAddress(ListenPort));
        //设为非阻塞模式
        listenChannel.configureBlocking(false);
        // 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作
        // 一个serversocket channel准备好接收新进入的连接称为“接收就绪”
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        **/
        for (int i=0; i<3; i++) //注册多个Channel
        {
// 打开监听信道
            ServerSocketChannel listenerChannel = ServerSocketChannel.open();
// 与本地端口绑定
            listenerChannel.socket().bind(new InetSocketAddress(ListenPort+i));
// 设置为非阻塞模式
            listenerChannel.configureBlocking(false);
// 注册到selector中
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
        }
        //反复循环，等待IO
        while (true){
            //等待信道就绪
            int keys = selector.select(TimeOut);
            //刚启动时连接输出0，client连接后一直输出1
            if(keys == 0){
                System.out.println("独自等待。。。");
                continue;
            }
            // 取得迭代器，遍历每一个注册的通道
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = set.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    // a connection was accepted by a ServerSocketChannel.
                    // 可通过Channel()方法获取就绪的Channel并进一步处理
                    SocketChannel channel = (SocketChannel) key.channel();
                }
                else if (key.isConnectable())
                {
                    // TODO
                }
                else if (key.isReadable())
                {
                    // TODO
                }
                else if (key.isWritable())
                {
                    // TODO
                }
                // 删除处理过的事件
                keyIterator.remove();
            }
        }
    }
}




























