package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName AddRequirementCommand
 * @Description: TODO
 * @create 2018-08-12 12:45
 */
public class AddRequirementCommand extends Command {
    //执行增加一项需求的命令
    @Override
    public void execute() {
        //找到需求组
        super.rg.find();
        //增加一份需求
        super.rg.add();
        //给出计划
        super.rg.plan();
    }


}
