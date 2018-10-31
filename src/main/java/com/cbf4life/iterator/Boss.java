package com.cbf4life.iterator;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Boss
 * @Description: 老板看到项目信息了
 * @create 2018-08-12 16:35
 */
public class Boss {
    public static void main(String[] args) {
        /*//定义一个List,存放所有的项目对象
        ArrayList<IProject> projectList = new ArrayList<IProject>();

        //增加星球大战项目

        projectList.add(new Project("星球大战项目", 10, 1000000));
        //增加扭转时空项目
        projectList.add(new Project("增加扭转时空项目", 10, 1000000));
        //增加超人改造项目
        projectList.add(new Project("增加超人改造项目", 10, 1000000));

        //这边100个项目
        for (int i = 4; i < 104; i++) {
            projectList.add(new Project("第" + i + "项目", i * 5, i * 1000000));
        }

        //遍历一下ArrayList,把所有的数据都取出
        for (IProject project : projectList) {
            System.out.println(project.getProjectInfo());
        }*/

        //定义一个List，存放所有的项目对象
        IProject project = new Project();

        //增加星球大战项目
        project.add("星球大战项目", 10, 1000000);
        project.add("增加扭转时空项目", 10, 1000000);
        project.add("增加超人改造项目", 10, 1000000);
        //这边100个项目
        for (int i = 4; i < 104; i++) {
            project.add("第" + i + "项目", i * 5, i * 1000000);
        }

        //遍历一下ArrayList,把所有的数据都取出
        IProjectIterator projectIterator = project.iterator();
        while (projectIterator.hasNext()) {
            IProject p = (IProject) projectIterator.next();
            System.out.println(p.getProjectInfo());

        }

    }
}
