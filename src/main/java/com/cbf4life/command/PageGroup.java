package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName PageGroup
 * @Description: 美工组的职责是设计出一套漂亮，简单、便捷的页面
 * @create 2018-08-05 22:41
 */
public class PageGroup extends Group{
    //首先这个美工组应该被找到吧，要不你跟谁谈
    @Override
    public void find() {
        System.out.println("找到美工组。。。");
    }

    //美工被要求增加一个页面
    @Override
    public void add() {
        System.out.println("客户要求增加一个页面");
    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个页面");
    }

    @Override
    public void change() {
        System.out.println("客户要求修改一个页面");
    }

    @Override
    public void plan() {
        System.out.println("客户要求变更计划");
    }

}
