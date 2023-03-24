package com.base.extend;

/**
 * @author xingkong
 * @date 2023/2/23 14:31
 */
public class Father {
    private String name;

    Father(){
        System.out.println("--父类的无参构造函数--");
    }

    Father(String name){
        this.name=name;
        System.out.println("--父类的有参构造函数--"+this.name);
    }

    static{
        System.out.println("--父类的静态代码块--");
    }

    {
        System.out.println("--父类的非静态代码块--");
    }

    public void speak(){
        System.out.println("--父类的方法--");
    }

    public static void main(String[] args) {
        System.out.println("--父类主程序--");
        Father father=new Father("父亲的名字");
        father.speak();
    }

}
