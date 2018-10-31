package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName OpenningState
 * @Description: 在电梯门开启的状态下能做什么时期
 * @create 2018-10-20 20:44
 */
public class OpenningState extends LIftState{
    @Override
    public void open() {
        System.out.println("电梯门开启。。。");
    }

    //开启当然可以关闭，我就想测试一下电梯门开关功能
    @Override
    public void close() {
        //状态修改
        super.context.setLiftState(context.closeingState);
        //动作委托为CloseState来执行
        super.context.getLiftState().close();

    }

    //门开着电梯就想跑，这电梯，吓死你！
    @Override
    public void run() {

    }

    //开门还不停止?
    @Override
    public void stop() {

    }
}
