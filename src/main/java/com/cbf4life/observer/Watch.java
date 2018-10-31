package com.cbf4life.observer;

/**
 * @author wujc
 * @ClassName Watch
 * @Description: 监控程序
 * @create 2018-08-13 23:11
 */
public class Watch extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi lisi;
    private String type;

    public Watch(HanFeiZi _hanFeiZi, LiSi _liSi, String _type) {
        this.hanFeiZi = _hanFeiZi;
        this.lisi = _liSi;
        this.type = _type;
    }

    @Override
    public void run() {
        while (true) {
            if (this.type.equals("breakfast")) { //监控是否在吃早餐
                if (this.hanFeiZi.isHaveBreakfast()) {
                    this.lisi.update("韩非子在吃饭");
                    //重置状态，继续监控
                    this.hanFeiZi.setHaveBreakfast(false);
                }
            }else { //监控是否有娱乐
                if (this.hanFeiZi.isHaveFun()) {
                    this.lisi.update("韩非子在娱乐");
                    this.hanFeiZi.setHaveFun(false);

                }
            }
        }
    }
}
