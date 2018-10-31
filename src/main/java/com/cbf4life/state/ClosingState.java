package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName ClosingState
 * @Description: 电梯门关闭以后，电梯可以做哪些事情
 * @create 2018-10-20 21:17
 */
public class ClosingState extends LIftState{
    @Override
    public void open() {
        super.context.setLiftState(Context.openingState);
        super.context.getLiftState().open();
    }

    //电梯门关闭，这是关闭状态要实现的动作
    @Override
    public void close() {
        System.out.println("电梯门关闭。。。");
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningState);//设置成运行状态
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}
