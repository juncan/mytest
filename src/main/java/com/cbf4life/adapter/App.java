package com.cbf4life.adapter;

/**
 * @author wujc
 * @ClassName App
 * @Description: 这就是我们具体的应用了，比如老板要查所有的20-30的女性信息
 * @create 2018-07-15 22:59
 */
public class App {
    public static void main(String[] args) {
        //没有与外系统连接的时候，是这样写的
        /*IUserInfo youngGirl = new UserInfo();
        //从数据库中查101个
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }*/

        //老板一想不对呀，兔子不吃窝边草，还是找人力资源的员工好点
        IUserInfo youngGirl = new OuterUserInfo();
        //从数据库中查101个
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }
    }
}
