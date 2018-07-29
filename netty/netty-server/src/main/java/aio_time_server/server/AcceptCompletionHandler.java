package aio_time_server.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandler> {

    /** 调用AsynchronousSocketChannel  的accept 方法后，如果有新的客户端连接接入，系统将回调我们传入的 CompletionHandler 实例的
     * completed 方法，表示新的客户端已经接入成功
     *因为一个AsynchronousServerSocketChannel可以接收成千上万个客户端，所以需要继续调用它的accept 方法，接收其他的客户端连接，最终
     * 形成一个循环
     *
     * **/
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {

        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
