package com.cbf4life.state;

/**
 * @author wujc
 * @ClassName Lift
 * @Description: 电梯的实现类
 * @create 2018-10-20 18:42
 */
public class Lift implements ILift{
    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {
        //电梯在什么状态才能开启
        switch (this.state){
            case OPENING_STATE: // 如果已经在门敞状态，则什么都不做
                break;
            case CLOSEING_STATE: //如果电梯关闭，则可以开启
                this.openWithoutLogic();
                this.setState(OPENING_STATE);
                break;
            case RUNING_STATE: //正在运行状态，则不能开门，什么都不做
                break;
            case STOPPING_STATE: //停止状态，淡然要开门:
                this.openWithoutLogic();
                this.setState(OPENING_STATE);
                break;
        }

    }

    @Override
    public void close() {
        //电梯在什么状态下才能关闭
        switch (this.state) {
            case OPENING_STATE: //如果是则可以关门，同时修改电梯状态
                this.closeWithoutLogic();
                this.setState(CLOSEING_STATE);
                break;
            case CLOSEING_STATE: //如果电梯就是关闭状态，则什么都不做:
                break;
            case RUNING_STATE: //如果是正在运行，门本来就是关闭的也说明都不做
                break;
            case STOPPING_STATE: //如果是停止状态，本也是关闭的，什么也不做
                break;
        }
    }

    @Override
    public void run() {
        switch (this.state) {
            case OPENING_STATE: //如果门已经在门敞状态，则不能运行，什么都不做
                break;
            case CLOSEING_STATE: //如果电梯关闭状态，则可以运行
                this.runWithoutLogic();
                this.setState(RUNING_STATE);
                break;
            case RUNING_STATE: //正在运行状态，则什么都不做
                break;
            case STOPPING_STATE: //停止状态，则可以运行
                this.runWithoutLogic();
                this.setState(RUNING_STATE);
                break;
        }
    }

    @Override
    public void stop() {
        switch (this.state) {
            case OPENING_STATE: //如果已经在门敞状态，那肯定要先停下来，什么都不做
                break;
            case CLOSEING_STATE: //如果电梯关门状态，则当然可以停下来
                this.stopWithoutLogic();
                this.setState(CLOSEING_STATE);
                break;
            case RUNING_STATE: //正在运行状态，有运行当然那也可以停止了
                this.stopWithoutLogic();
                this.setState(RUNING_STATE);
                break;
            case STOPPING_STATE: //停止状态，什么都不做
                break;

        }

    }
    //纯粹的店门关，不考虑任何条件
    private void closeWithoutLogic () {
        System.out.println("电梯门关闭。。。");
    }

    //纯粹的店门开，不考虑任何条件
    private void openWithoutLogic() {
        System.out.println("电梯门开启。。。");
    }

    //纯粹的停止，不考虑任何条件
    private void stopWithoutLogic() {
        System.out.println("电梯停止了。。。");
    }
    //纯粹的运行不考虑其他条件
    private void runWithoutLogic() {
        System.out.println("电梯上下跑起来了。。");
    }
}
