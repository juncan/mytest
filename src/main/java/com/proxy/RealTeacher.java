package com.proxy;

/**
 * @author xingkong
 * @date 2022/6/11 19:59
 */
public class RealTeacher implements Teacher{
    @Override
    public void teach() {
        System.out.println("i am a teacher,i am teaching");
    }
}
