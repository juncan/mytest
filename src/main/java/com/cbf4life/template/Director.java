package com.cbf4life.template;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Director
 * @Description: 导演安排顺序，生产车辆模型
 * @create 2018-07-18 23:08
 */
public class Director {
    private ArrayList<String> sequece = new ArrayList<>();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /**
     * A类型的奔驰车辆模型，先start，然后stop，其他引擎，喇叭一概没有
     */
    public BenzModel getABenzModel() {
        //清理场景，这里是一些初级程序员不注意的地方
        this.sequece.clear();

        //这只ABenzModel的执行顺序
        this.sequece.add("start");
        this.sequece.add("stop");

        //按照顺序返回一个奔驰车
        this.benzBuilder.setSequeue(this.sequece);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /**
     * B型号的奔驰车辆模型，是先发动引擎，然后启动，然后停止，没有喇叭
     */
    public BenzModel getBBenzModel() {
        this.sequece.clear();
        this.sequece.add("engine boom");
        this.sequece.add("start");
        this.sequece.add("stop");
        this.benzBuilder.setSequeue(this.sequece);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /**
     * C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
     */
    public BMWModel getCBMWModel() {
        this.sequece.clear();

        this.sequece.add("alarm");
        this.sequece.add("start");
        this.sequece.add("stop");

        this.bmwBuilder.setSequeue(this.sequece);

        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    /**
     * D类型的宝马只有一个功能，就是跑，启动起来就跑，永远不停止，牛叉
     */
    public BMWModel getDBMWModel() {
        this.sequece.clear();

        this.sequece.add("start");

        this.bmwBuilder.setSequeue(this.sequece);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }
}
