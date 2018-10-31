package com.cbf4life.template;

/**
 * @author wujc
 * @ClassName HummerModel
 * @Description: Model 是悍马车辆模型的意思，不是悍马美女车模
 * @create 2018-07-18 20:56
 */
public abstract class HummerModel {
    /**
     * 首先，这个模型要能够被发送起来，别管是手发动，还是电力发动，
     * 反正是要能够发动起来，那这个实现要在实现类里了
     */
    protected abstract void start();

    //能发动，那还要能停下来，那才是真本本事
    protected abstract void stop();

    //喇叭会出声音，是滴滴叫，还是哞哞叫
    protected abstract void alarm();

    //引擎会混隆隆的响，不响是假的
    protected abstract void engineBoom();

    //那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    final public void run() {
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //然后就开始跑了，跑的过程中遇到一条狗挡路，就按喇叭
        if (this.isAlarm()) {
            this.alarm();
        }

        //到底目的地就停车
        this.stop();
    }

    //钩子方法，默认喇叭是会响的
    protected boolean isAlarm() {
        return true;
    }
}
