package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName RunningState
 * @Description: 电梯在运行状态下能做哪些动作
 * @create 2018-10-20 21:23
 */
public class RunningState extends LIftState{
    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("电梯上下跑。。。");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState); //环境设置成停止状态
        super.context.getLiftState().stop();
    }
}
