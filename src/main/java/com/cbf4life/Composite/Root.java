package com.cbf4life.Composite;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Root
 * @Description: 根节点的实现类
 * @create 2018-08-12 19:45
 */
public class Root implements IRoot{

    //保存根节点下的树枝节点和树叶节点，Subordinate的意思是下级
    private ArrayList subordinateList = new ArrayList();
    //根节点的名称
    private String name = "";
    //根节点的职位
    private String position = "";
    //根节点的薪水
    private int salary = 0;

    //通过构造函数传递进来总经理的信息
    public Root(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        String info = "";
        info = "名称：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水：" + this.salary;
        return info;
    }


    //增加树枝节点
    @Override
    public void add(IBranch branch) {
        this.subordinateList.add(branch);
    }

    //增加叶子节点，比如秘书，直接隶属于总计里
    @Override
    public void add(ILeaf leaf) {
        this.subordinateList.add(leaf);
    }

    @Override
    public ArrayList getSubordinateInfo() {
        return this.subordinateList;
    }

}
