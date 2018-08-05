package netty_time_server.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

//    private final ByteBuf firstMessage;

    private byte[] req;

    private int counter;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        this.firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
    }

    /**
     *    当客户端和服务端TCP链路建立成功之后，Netty的NIO 线程会调用 channelActive 方法发送查询时间的指令给服务端，
     *
     *    调用ChannelHandlerContext 的writeAndFlush 方法将请求消息发送给服务端
     *
     *    当服务端返回应答消息时，channelRead 方法 被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf message;
        for(int i = 0;i< 100;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);//每发送一条就刷新一次，保证每条消息都会被写入Channel中。

        }
//        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;//msg已经是解码成字符串之后的应答消息了
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);


        /**
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);

        **/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
