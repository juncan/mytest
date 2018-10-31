package com.cbf4life.iterator;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Project
 * @Description: TODO
 * @create 2018-08-12 16:30
 */
public class Project implements IProject {
    //定义一个项目列表，说有的项目都放在这里
    private ArrayList<IProject> projectArrayList = new ArrayList<IProject>();
    //项目名称
    private String name = "";

    //项目成员数量
    private int num = 0;

    //项目费用
    private int cost = 0;

    //定义一个构造函数，把所有老爸需要看到的信息存储起来
    public Project(String name, int num, int cost) {
        this.name = name;
        this.num = num;
        this.cost = cost;
    }

    public Project() {

    }

    @Override
    public void add(String name, int num, int cost) {
        this.projectArrayList.add(new Project(name, num, cost));
    }

    @Override
    public String getProjectInfo() {
        String info = "";

        //获得项目的名称
        info = info + "项目名称是：" + this.name;

        //获得项目人数
        info = info + "\t项目人数：" + this.name;

        //项目费用

        info = info + "\t项目费用:" + this.cost;

        return info;
    }

    @Override
    public IProjectIterator iterator() {
        return new ProjectIterator(this.projectArrayList);
    }
}
