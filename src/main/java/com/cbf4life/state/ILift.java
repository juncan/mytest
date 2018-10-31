package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName ILift
 * @Description: 定义一个电梯的接口
 * @create 2018-10-20 18:40
 */
public interface ILift {
    //电梯的四个状态
    public final static int OPENING_STATE = 1;
    public final static int CLOSEING_STATE = 2;
    public final static int RUNING_STATE = 3;
    public final static int STOPPING_STATE = 4;

    //设置电梯的状态
    public void setState(int state);

    //首先电梯门开启的动作
    public void open();

    //电梯门有开启，那当然也就有关闭了
    public void close();

    //电梯要能上能下，跑起来
    public void run();

    //电梯还要能停下来，停不下来那就扯淡了
    public void stop();
}
