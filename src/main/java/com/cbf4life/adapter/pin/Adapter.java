package com.cbf4life.adapter.pin;

/**
 * @author xingkong
 * @date 2023/3/29 18:03
 */
public class Adapter implements TriplePin {

    private DualPin dualPinDevice;

    //创建适配器时，需要把两插设备接入进来
    public Adapter(DualPin dualPinDevice) {
        this.dualPinDevice = dualPinDevice;
    }

    //适配器实现的是目标接口
    @Override
    public void electrify(int l, int n, int e) {
        //调用被适配设备的两插通电方法，忽略地线参数e
        dualPinDevice.electrify(l, n);
    }

}
