public class Main {
    public static void main(String[] args)throws Exception {
//        PlainOioServer plainOioServer = new PlainOioServer();
//        PlainNioServer plainOioServer = new PlainNioServer();
        NettyOioServer plainOioServer = new NettyOioServer();
        plainOioServer.server(8080);
    }
}
