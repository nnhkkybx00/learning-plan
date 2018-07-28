package nio_interruptblesocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new InterruptibleSocketFrame();
                frame.setTitle("InterruptibleSocketTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}



class InterruptibleSocketFrame extends JFrame{
    public static final  int TEXT_ROWS = 20;
    public static final  int TEXT_COLUMNS = 60;
    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame(){
        JPanel northPanel = new JPanel();
        add(northPanel,BorderLayout.NORTH);

        messages = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        interruptibleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                          connectInterruptible();
                        }catch (IOException e1){
                            messages.append("\nInterruptibleSocketTest.connecInterruptibly: " + e1);
                        }
                    }
                });
                connectThread.start();
            }
        });

        blockingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            connectBlocking();
                        }catch (IOException e1){
                            messages.append("\nInterruptibleSocketTest.connectBlocking: " + e1);
                        }
                    }
                });
                connectThread.start();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectThread.interrupt();
                cancelButton.setEnabled(false);
            }
        });
        server = new TestServer();
        new Thread(server).start();
        pack();
    }

    /**
     * Connects to the test server,using interruptible I/O
     * @throws IOException
     */
    public void connectInterruptible() throws IOException{
        messages.append("Interruptible:\n");
        try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8189))){
            in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted()){
                messages.append("Reading \n");
                if(in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Channel closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }



    /**
     * Connects to the test server,using blocking I/O
     * @throws IOException
     */
    public void connectBlocking() throws IOException{
        messages.append("Blocking:\n");
        try(Socket socket = new Socket("localhost",8189)){
            in = new Scanner(socket.getInputStream());
            int count = 0;
            while (!Thread.currentThread().isInterrupted()){

                messages.append("Reading "+ count ++ );
                if(in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Socket closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
}




    class TestServer implements Runnable{
        @Override
        public void run() {
            try {
                ServerSocket s = new ServerSocket(8189);
                while (true){
                    Socket incoming = s.accept();
                    Runnable r = new TestServerHandler(incoming);;
                    Thread thread = new Thread(r);
                    thread.start();
                }
            }catch (IOException e){
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    class TestServerHandler implements Runnable{
        private Socket incoming;
        private int counter;

        public TestServerHandler(Socket socket){
            this.incoming = socket;
        }

        @Override
        public void run() {
            try{
                try{
                    OutputStream outputStream = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(outputStream,true /*autoFlush*/);
                    while (counter < 100){
                        counter ++;
                        if(counter <= 10) out.println(counter);
                        Thread.sleep(100);
                    }
                }finally {
                    incoming.close();
                    messages.append("Closing server\n");
                }
            }catch (Exception e){
                messages.append("\nTestServerHandler.run: " + e);
            }
        }
    }
}
