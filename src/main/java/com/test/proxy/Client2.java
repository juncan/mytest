package com.test.proxy;

/**
 * @author wujc
 * @ClassName Client2
 * @Description: TODO
 * @create 2019-02-27
 */
public class Client2 {
    public static void main(String[] args) {
        Star realStar = new RealStar();
        Star proxy = (Star) new CglibProxyhandler().getProxyInstance(realStar);
        proxy.sing();
    }
}
