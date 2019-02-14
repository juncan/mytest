package com.cbf4life.prototype;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Thing
 * @Description: TODO
 * @create 2018-11-10 15:38
 */
public class Thing implements Cloneable {

    //定义一个私有变量
    private ArrayList<String> arrayList = new ArrayList<String>();

    public Thing() {
        System.out.println("构造函数被执行了");
    }

    //设置HashMap的值
    public void setValue(String value) {
        this.arrayList.add(value);
    }

    //取得arrayList的值
    public ArrayList<String> getValue() {
        return this.arrayList;
    }

    @Override
    protected Thing clone() {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
            thing.arrayList = (ArrayList) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();

        }
        return thing;
    }
}
