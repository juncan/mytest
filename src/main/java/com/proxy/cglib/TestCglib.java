package com.proxy.cglib;

/**
 * @author xingkong
 * @date 2022/6/24 23:35
 */
public class TestCglib {
    public static void main(String[] args) {
        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl bookFaceImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookFaceImpl.addUser();

    }
}
