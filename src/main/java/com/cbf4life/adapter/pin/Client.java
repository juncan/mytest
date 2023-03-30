package com.cbf4life.adapter.pin;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/3/29 18:02
 */
public class Client {
    public static void main(String[] args) {
        //TriplePin triplePinDevice = new TV(); //接口不兼容，此处报错“类型不匹配”
        DualPin dualPinDevice = new TV();//构造两插电视机
        TriplePin triplePinDevice = new Adapter(dualPinDevice);//适配器接驳两端
        triplePinDevice.electrify(1, 0, -1);//此处调用的是三插通电标准
        //输出结果：
        //火线通电：1，零线通电：0
        //电视开机
    }

}

