package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName Client
 * @Description: 模拟电梯的动作
 * @create 2018-10-20 18:48
 */
public class Client {
    public static void main(String[] args) {
        /*ILift lift = new Lift();

        //电梯的初始条件应该是停止状态
        lift.setState(ILift.STOPPING_STATE);

        //首先是电梯门开启，人进去
        lift.open();

        //然后电梯门关闭
        lift.close();

        //再然后，电梯跑起来，向上或者向下
        lift.run();

        //最后到达目的地，电梯停下来
        lift.stop();*/

        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();

    }
}
