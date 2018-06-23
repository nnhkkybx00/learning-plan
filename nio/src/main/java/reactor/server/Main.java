package reactor.server;

import java.io.IOException;

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
 * @date 2018/6/20 19:24 星期三
 */


public class Main {
    public static void main(String[] args) {
        try
        {
            Reactor temp = new Reactor(12345);
            temp.run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
