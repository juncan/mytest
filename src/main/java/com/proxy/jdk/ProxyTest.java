package com.proxy.jdk;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2022/6/11 20:04
 */
public class ProxyTest {
    public static void main(String[] args) {
        MyProxy myProxy = new MyProxy();

        Teacher teacher = (Teacher) myProxy.bind(new RealTeacher());

        teacher.teach();
    }
}
