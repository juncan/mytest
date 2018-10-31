package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName LIftState
 * @Description: 定义一个电梯的接口
 * @create 2018-10-20 20:40
 */
public abstract class LIftState {
    //定义一个环境角色，也就是封装状态的变换引起的功能变化
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    //首先电梯门开启动作
    public abstract void open();

    //电梯门有开启，那当然也就有关闭了
    public abstract void close();

    //电梯要能上能下，跑起来
    public abstract void run();

    //电梯还要能停下来，停不下来那就扯犊子了
    public abstract void stop();

}
