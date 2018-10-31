package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName StoopingState
 * @Description: 在停止状态下能做什么事情
 * @create 2018-10-20 21:25
 */
public class StoppingState extends LIftState{
    @Override
    public void open() {
        super.context.setLiftState(Context.openingState);
        super.context.getLiftState();
    }

    //停止状态开门？电梯门本来就是关着的！
    @Override
    public void close() {

    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了。。。");

    }
}
