package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName Cietnt
 * @Description: 客户就是甲方，给我们钱的一方，是老大
 * @create 2018-08-05 23:17
 */
public class Cietnt {
    public static void main(String[] args) {
        /*//首先客户找到需求组说，过来谈需求，并修改
        System.out.println("=====客户要求增加一个需求。。。");
        Group rg = new RequirementGroup();
        //找到需求组
        rg.find();
        //增加一个需求
        rg.add();
        //要求变更计划
        rg.plan();

        //首选找到美工组说，过来逃页面，并修改。。。
        System.out.println("----------客户要求删除一个页面-----");
        Group pg = new PageGroup();
        //找到需求组
        pg.find();
        //增加一个需求
        pg.add();
        //需求变更计划
        pg.plan();*/

        //定义我们的接头人
        Invoker xiaoSan = new Invoker(); //接头人就是我小三
        //客户要求增加一项需求
        System.out.println("-----------------客户要求增加一项需求---------");
        //客户给我们下命令来
        Command command = new AddRequirementCommand();

        //接头人接收到命令
        xiaoSan.setCommand(command);

        //接头人执行命令
        xiaoSan.action();
    }
}
