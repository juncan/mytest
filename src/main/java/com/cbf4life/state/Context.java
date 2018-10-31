package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName Context
 * @Description: TODO
 * @create 2018-10-20 21:00
 */
public class Context {
    //定义出所有的电梯状态
    public final static OpenningState openingState = new OpenningState();
    public final static ClosingState closeingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();

    //定义一个当前电梯状态
    private LIftState lIftState;

    public LIftState getLiftState() {
        return lIftState;
    }

    public void setLiftState(LIftState lIftState) {
        this.lIftState = lIftState;
        //把当前的环境通知到各个实现类中
        this.lIftState.setContext(this);

    }

    public void open() {
        this.lIftState.open();
    }

    public void close() {
        this.lIftState.close();
    }

    public void run() {
        this.lIftState.run();
    }

    public void stop() {
        this.lIftState.stop();
    }




}
