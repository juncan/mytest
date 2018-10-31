package com.cbf4life.command;

/**
 * @author wujc
 * @ClassName Invoker
 * @Description: TODO
 * @create 2018-08-12 15:16
 */
public class Invoker {
    //什么命令
    private Command command;
    //客户发出命令
    public void setCommand(Command command) {
        this.command = command;
    }
    //执行客户的命令
    public void action() {
        this.command.execute();
    }


}
