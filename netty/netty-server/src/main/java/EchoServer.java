import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * EchoServerHandler 实现了业务逻辑
 * main() 方法引导了服务器
 * 引导过程所需要的步奏如下：
 * 创建一个ServerBootstrap 的实例以引导和绑定服务器
 * 创建并分配一个NioEventLoopGroup 实例以进行事件的处理，如接受新连接以及读/写数据
 * 指定服务器绑定的本地InetSocketAddress；
 * 使用一个EchoServerHandler 的实例初始化每一个新的Channel;
 * 调用ServerBootstrap.bind() 方法以绑定服务器
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main (String[] args) throws Exception{
//        if(args.length != 1){
//            System.err.println(
//                    "Usage: " + EchoServer.class.getSimpleName() + "<port>"
//            );
//            return;
//        }
//        int port = Integer.parseInt(args[0]);
        int port = Integer.parseInt("9999");
        new EchoServer(port).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            //创建一个ServerBootstrap 的实例以引导和绑定服务器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)//创建并分配一个NioEventLoopGroup 实例以进行事件的处理，如接受新连接以及读/写数据
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))//指定服务器绑定的本地InetSocketAddress；
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override//使用一个EchoServerHandler 的实例初始化每一个新的Channel;
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();//调用ServerBootstrap.bind() 方法以绑定服务器
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
