package com.cbf4life.Composite.file;

/**
 * @author xingkong
 * @date 2023/3/27 15:29
 */
public abstract class Node {
    protected String name;//节点命名

    public Node(String name) {//构造方法需传入节点名
        this.name = name;
    }

    //增加后续子节点方法
    protected abstract void add(Node child);

    protected void tree(int space) {
        for (int i = 0; i < space; i++) {
            System.out.print("  ");//先循环输出space个空格
        }
        System.out.println(name);//接着再输出自己的名字
    }

    //无参重载方法，默认从第0列开始展示
    protected void tree() {
        this.tree(0);
    }
}
