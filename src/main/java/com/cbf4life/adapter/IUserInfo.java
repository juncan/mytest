package com.cbf4life.adapter;

/**
 * @author wujc
 * @ClassName IUserInfo
 * @Description: 用户信息对象
 * @create 2018-07-15 22:50
 */
public interface IUserInfo {
    //获取用户姓名
    public String getUserName();

    //获取家庭住址
    public String getHomeAddress();

    //手机号码，这个太重要了，手机泛滥呀
    public String getMobileNumber();

    //办公电话，一般式座机
    public String getOfficeTelNumber();

    //这个人的职位是啥
    public String getJobPosition();

    //获取家庭电话，这个有点缺德，我是不喜欢打家庭电话讨论工作
    public String getHomeTelNumber();

}
