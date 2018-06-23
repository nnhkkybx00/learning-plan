package buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ━━━━━━如来保佑━━━━━━
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 *
 * @author Ledev4
 * @date 2018/6/20 16:38 星期三
 */


public class IO_Demo {
    public static void main(String[] args) throws Exception{
//        String inFile = "E:\\daydayup\\daydayup\\nio\\src\\main\\resources\\buffer.txt";
        String inFile = "E:\\daydayup\\daydayup\\nio\\src\\main\\resources\\data.txt";
        String outFile = "E:\\daydayup\\daydayup\\nio\\src\\main\\resources\\bufferout.txt";

        FileInputStream fin = new FileInputStream(inFile);
        FileOutputStream fout = new FileOutputStream(outFile);

        //获取输入输出通道
        FileChannel fileChanneIn = fin.getChannel();
        FileChannel fileChanneOut = fout.getChannel();

        //创建缓冲区，分配1k堆内存
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){

            //clear方法重设缓冲区，使他可以接受读入得数据
            buffer.clear();


            // 从输入通道中读取数据并写入buffer
            int r = fileChanneIn.read(buffer);
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
            if(r == -1){
                break;
            }
            // flip方法将 buffer从写模式切换到读模式
            buffer.flip();
            // 从buffer中读取数据然后写入到输出通道中
            fileChanneOut.write(buffer);
        }
        //关闭通道
        fileChanneIn.close();
        fileChanneOut.close();
        fout.close();
        fin.close();

    }
}







































