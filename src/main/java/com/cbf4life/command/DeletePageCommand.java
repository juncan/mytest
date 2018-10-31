package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName DeletePageCommand
 * @Description: 删除一个页面的命令ODO
 * @create 2018-08-12 13:06
 */
public class DeletePageCommand extends Command{
    //执行删除一个页面的命令
    @Override
    public void execute() {
        //找到页面组
        super.pg.find();
        //删除一个页面
        super.rg.delete();
        //给出计划
        super.rg.plan();
    }
}
