package com.cbf4life.adapter;

/**
 * @author wujc
 * @ClassName UserInfo
 * @Description: TODO
 * @create 2018-07-15 22:56
 */
public class UserInfo implements IUserInfo {
    @Override
    public String getUserName() {
        System.out.println("姓名叫做。。。");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("这里是员工的家庭住址。。。");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("这个人的手机号码是0000.。。");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("办公室电话是。。。");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("这个人的职位是Boss...");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("员工的家庭电话是。。。");
        return null;
    }
}
