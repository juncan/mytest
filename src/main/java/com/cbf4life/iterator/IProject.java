package com.cbf4life.iterator;

/**
 * @author wujc
 * @ClassName IProject
 * @Description: 定义一个接口，所有的项目都是一个接口
 * @create 2018-08-12 16:29
 */
public interface IProject {

    //增加项目
    public void add(String name, int num, int cost);
    //从老爸那边看到的就是项目信息
    public String getProjectInfo();

    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
