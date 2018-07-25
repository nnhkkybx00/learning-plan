import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyOioServer {
    public void server(int port) throws Exception{
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi\r\n", Charset.forName("UTF-8"))
        );
//        EventLoopGroup group = new OioEventLoopGroup();//阻塞
        EventLoopGroup group = new NioEventLoopGroup();//非阻塞
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
//                    .channel(OioServerSocketChannel.class)//阻塞
                    .channel(NioServerSocketChannel.class) //非阻塞
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                              new ChannelInboundHandlerAdapter(){
                                  @Override
                                  public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                      ctx.writeAndFlush(buf.duplicate())
                                              .addListener(ChannelFutureListener.CLOSE);
                                  }
                              }
                            );
                        }
                    });
            ChannelFuture future = b.bind().sync();
            future.channel().closeFuture().sync();

        }finally {
         group.shutdownGracefully().sync();
        }
    }
}
