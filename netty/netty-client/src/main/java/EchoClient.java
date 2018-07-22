import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 初始化客户端，创建一个Bootstrap实例
 * 为进行事件处理分配了一个 NioEventLoopGroup 实例，其中事件处理包括创建新的连接以及处理入站和出站数据
 * 为服务器创建了一个 InetSocketAddress 实例
 * 当连接建立时，一个 EchoClientHandler 实例会被安装到ChannelPipeline中
 * 在一切设置完成后，调用 bootstrap.connect() 方法连接到远程节点
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)  //指定EventLoopGroup 以处理客户端事件，需要适用于NIO的实现
                    .channel(NioSocketChannel.class)//适用于NIO传输的Channel类型
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
//        if(args.length != 2){
//            System.err.println(
//                    "Usage: " + EchoClient.class.getSimpleName() + "<host> <port>"
//            );
//            return;
//        }
//        String host = args[0];
//        int port = Integer.parseInt(args[1]);
        new EchoClient("localhost",9999).start();
    }
}
