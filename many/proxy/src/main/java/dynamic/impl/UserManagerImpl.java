package dynamic.impl;


import dynamic.UserManager;

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
 * @date 2018/6/23 17:44 星期六
 */


public class UserManagerImpl implements UserManager {

    public void addUser(String userId, String userName) {
        System.out.println("UserManagerImpl.addUser");
    }

    public void delUser(String userId) {
        System.out.println("UserManagerImpl.delUser");
    }

    public String findUser(String userId) {
        System.out.println("UserManagerImpl.findUser");
        return "张三";
    }

    public void modifyUser(String userId, String userName) {
        System.out.println("UserManagerImpl.modifyUser");
    }
}
