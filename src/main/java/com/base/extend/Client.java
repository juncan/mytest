package com.base.extend;

/**
 * @author xingkong
 * @date 2023/2/23 14:34
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("--子类主程序--");
        Father father1=new Son("儿子的名字");
        father1.speak();
    }
}
