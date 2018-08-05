package netty_time_server.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //改造之后
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():
                "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);

        /**  改造之前
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8")
                .substring(0,req.length - System.getProperty("line.separator").length());
        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():
                "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
         **/
    }





    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**  作用是将消息发送队列中的消息写入到SocketChannel 中发送给对方
         *      从性能角度考虑，为了防止频繁地唤醒Selector 进行消息发送，Netty 的write 方法并不直接将消息写入
         *      SocketChannel，调用write 方法只是把待发送的消息放到发送缓冲数组中，再调用flush 方法，将发送缓冲区中的消息全部写到SocketChannel中
         *
         *
         *
         * **/
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
