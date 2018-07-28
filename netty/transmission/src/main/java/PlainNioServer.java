

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {
    public void server(int port)throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);

        ServerSocket serverSocket = socketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);


        Selector selector = Selector.open();
        socketChannel.register(selector,SelectionKey.OP_ACCEPT);


        final ByteBuffer  msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        for(;;){
            try {
                selector.select();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();

                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE | SelectionKey.OP_READ,msg.duplicate());

                        System.out.println(
                                "Accepted connection from " + client
                        );
                    }

                    if(key.isReadable()){
                        ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
                        inputBuffer.clear();

                        SocketChannel socketChannel1 = (SocketChannel)key.channel();
                        int numBytes = socketChannel1.read(inputBuffer); // 读取数据
                        if (numBytes == -1)
                        {
                            System.out.println("[Warning!] A client has been closed.");
                            socketChannel1.close();
                            return;
                        }
                        String str = new String(inputBuffer.array());
                        System.out.println(socketChannel1.socket().getRemoteSocketAddress().toString() + " > " + str);
                    }


                        if(key.isWritable()){
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
                            while (byteBuffer.hasRemaining()){
                                if(client.write(byteBuffer) == 0){
                                    break;
                                }
                            }
                        }

                }catch (IOException e){
                    key.cancel();
                    try {
                        key.channel().close();
                    }catch (IOException e1){
                        //
                    }
                }
            }
        }
    }
}
