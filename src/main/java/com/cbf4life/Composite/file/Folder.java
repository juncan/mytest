package com.cbf4life.Composite.file;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹类
 *
 * @author xingkong
 * @date 2023/3/27 15:36
 */
public class Folder extends Node {
    //文件夹可以包含子节点（子文件夹或者文件）
    private List<Node> childrenNodes = new ArrayList<>();

    public Folder(String name) {
        super(name);//调用父类“节点”的构造方法命名
    }

    @Override
    protected void add(Node child) {
        childrenNodes.add(child);//可以添加子节点
    }

    @Override
    public void tree(int space) {
        super.tree(space);//调用父类通用的tree方法列出自己的名字
        space++;//在循环的子节点前，空格数要加1
        for (Node node : childrenNodes) {
            node.tree(space);//调用子节点的tree方法
        }
    }
}

