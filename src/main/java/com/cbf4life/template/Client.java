package com.cbf4life.template;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Client
 * @Description: 客户开始使用这个模型
 * @create 2018-07-18 21:40
 */
public class Client {
    public static void main(String[] args) {
        /*//客户开着H1型号，出去遛弯了
        HummerH1Model h1 = new HummerH1Model();
        h1.setAlarmFlag(true);
        h1.run();

        //客户开着H2型号，出去玩耍了
        HummerModel h2 = new HummerH2Model();
        h2.run();*/

        /**
         * 客户告诉牛叉公司，我要这样一个模型，然后牛叉公司就告诉我老大
         * 说要这样一个模型，这样一个顺序，然后我就来制造
         */
        //BenzModel benz = new BenzModel();
//        ArrayList<String> sequence = new ArrayList<String>(); //存放run的顺序
//        sequence.add("engine boom");  //客户要求，run的时候先发动引擎
//        sequence.add("start"); //启动起来
//        sequence.add("stop"); //开了一段就停下来
//
//        /*//然后我们把这个顺序给奔驰车
//        benz.setSequence(sequence);
//        benz.run();*/
//
//        //要一个奔驰车
//        BenzBuilder benzBuilder = new BenzBuilder();
//        //把顺序给这个builder类，制造出 这样一个车出来
//        benzBuilder.setSequeue(sequence);
//
//        //制造出一个奔驰车
//        BenzModel benz = (BenzModel) benzBuilder.getCarModel();
//        benz.run();

        Director director = new Director();

        //1w辆A类型的奔驰车
        for (int i = 0; i < 10000; i++) {
            director.getABenzModel().run();
        }

        //100W辆B类型的奔驰车
        for (int i = 0; i < 1000000; i++) {
            director.getBBenzModel().run();
        }

        //1000W辆C类型的宝马车
        for (int i = 0; i < 10000000; i++) {
            director.getCBMWModel().run();
        }

    }
}
