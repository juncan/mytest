package com.base.extend;

/**
 * @author xingkong
 * @date 2023/2/23 14:32
 */
public class Son extends Father {
    private String name;

    static{
        System.out.println("--子类的静态代码块--");
    }

    {
        System.out.println("--子类的非静态代码块--");
    }

    Son(){
        System.out.println("--子类的无参构造函数--");
    }

    Son(String name){
        this.name=name;
        System.out.println("--子类的有参构造函数--"+this.name);
    }

    @Override
    public void speak(){
        System.out.println("--子类Override了父类的方法--");
    }

    public static void main(String[] args) {
        System.out.println("--子类主程序--");

        Son son=new Son("儿子的名字");
        son.speak();
    }
}
