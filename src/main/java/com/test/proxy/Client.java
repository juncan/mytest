package com.test.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-02-27
 */
public class Client {
    public static void main(String[] args) {
        Star realStar = new RealStar();
        //创建一个代理对象实例
        Star proxy = (Star) new JdkProxyHander(realStar).getProxyInstance();

        proxy.sing();

        //写文件
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Star.class.getInterfaces());
        String path = "C:/Users/Administrator/Desktop/StarProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
