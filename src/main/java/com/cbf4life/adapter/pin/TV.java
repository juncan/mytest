package com.cbf4life.adapter.pin;

/**
 * @author xingkong
 * @date 2023/3/29 18:01
 */
public class TV implements DualPin {
    @Override
    public void electrify(int l, int n) {
        System.out.print("火线通电：" + l + "，零线通电：" + n);
        System.out.println("电视开机");
    }
}

