package netty_time_server.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


public class TiemServer {

    public static void main(String[] args) throws Exception{
        new TiemServer().bind(8080);
    }





   public void bind(int port) throws Exception{
       //配置服务端的NIO线程池
       //是一组NIO线程，专门用于网络事件的处理，实际上他们就是Reactor线程组。
       EventLoopGroup bossGroup = new NioEventLoopGroup();//用于服务端接收客户端的连接
       EventLoopGroup workerGroup = new NioEventLoopGroup();//用于SocketChannel 的网络读写   ？？？？？  怎么体现区别？？？？？
       try{
           ServerBootstrap b = new ServerBootstrap();//启动NIO服务服务端的辅助启动类，目的：降低服务端的开发复杂度
           b.group(bossGroup,workerGroup)
                   .channel(NioServerSocketChannel.class)//对应于JDK NIO 类库中的 ServerSocketChannel类
                   .option(ChannelOption.SO_BACKLOG,1024)//TCP参数
                   .childHandler(new ChildChannelHandler());//绑定I/O事件的处理类，类似于Reactor模式中的Handler类，主要用于处理网络I/O 事件，例如记录日志，对消息进行编解码
           //绑定端口，同步等待成功
           ChannelFuture f = b.bind(port).sync();
        //ChannelFuture用于异步操作的通知回调
           //等待服务端监听端口关闭
           f.channel().closeFuture().sync();
       }finally {
           //优雅退出
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
   }



    private class  ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {

            //LineBasedFrameDecoder的原理：依次遍历ByteBuf 中的可读字节，判断是否有“\n”或者“\r\n”，有就以此为结束位置
            // 从可读索引到结束位置区间的字节就组成了一行。它是以换行符为结束标志的解码器
            // 读到最大长度后没有发现换行符，就抛异常，同时忽略之前读到的异常码流
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());//将接收到的对象转换成字符串
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

}


