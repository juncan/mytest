package com.cbf4life.Composite;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Branch
 * @Description: 所有的树枝节点
 * @create 2018-08-12 19:54
 */
public class Branch implements IBranch,ICorp {
    //存储子节点的信息
    private ArrayList<ICorp> subordinateList = new ArrayList<ICorp>();

    //树枝节点的名称
    private String name = "";
    //树枝节点的职位
    private String position = "";
    //树枝节点的薪水
    private int salary = 0;

    public Branch(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void addSubordinate(ICorp corp) {
        this.subordinateList.add(corp);
    }

    //我有哪些下属
    @Override
    public ArrayList<ICorp> getSubordinateInfo() {
        return this.subordinateList;
    }

    @Override
    public String getInfo() {
        String info = "";
        info = "姓名：" + this.name;
        info = info + "\n职位：" + this.position;
        info = info + "\n薪水：" + this.salary;
        return info;
    }
}
