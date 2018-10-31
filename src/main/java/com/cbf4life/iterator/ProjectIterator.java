package com.cbf4life.iterator;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName ProjectIterator
 * @Description: 定义一个迭代器
 * @create 2018-08-12 17:04
 */
public class ProjectIterator implements IProjectIterator{
    //所有的项目都放在这里ArrayList中
    private ArrayList<IProject> projectList = new ArrayList<IProject>();
    private int currentItem = 0;

    public ProjectIterator(ArrayList<IProject> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean hasNext() {
        //定义一个返回值
        boolean b = true;
        if (this.currentItem >= projectList.size()
                || this.projectList.get(this.currentItem) == null) {
            b = false;
        }
        return b;
    }

    @Override
    public Object next() {
        return (IProject) this.projectList.get(this.currentItem++);
    }

}
