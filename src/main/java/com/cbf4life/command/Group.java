package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName Group
 * @Description: 项目组分成了三组，每个组还要接受增删改的命令
 * @create 2018-08-05 22:30
 */
public abstract class Group {
    //甲乙双方分开办公，你要和那个组讨论，你首先要找到那个组
    public abstract void find();

    //被要求增加功能
    public abstract void add();

    //被要求删除功能
    public abstract void delete();

    //被要求修改功能
    public abstract void change();

    //被要求给出所有的变更计划
    public abstract void plan();
}
