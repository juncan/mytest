package com.cbf4life.template;

/**
 * @author wujc
 * @ClassName HummerH1Model
 * @Description: 悍马车是每个越野者的最爱，其中H1最接近军用系列
 * @create 2018-07-18 21:24
 */
public class HummerH1Model extends HummerModel {
    private boolean alarmFlag = true; //是否要响喇叭

    @Override
    public void start() {
        System.out.println("悍马H1发动");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车。。。");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H1鸣笛。。。");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H1引擎声音是这样。。。");
    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    public void setAlarmFlag(boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }
}
