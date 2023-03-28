package com.cbf4life.decorate.paint;

/**
 * @author xingkong
 * @date 2023/3/28 10:03
 */
public class FoundationMakeup extends Decorator {

    public FoundationMakeup(Showable showable) {
        super(showable);//调用抽象父类的构造注入
    }

    @Override
    public void show() {
        System.out.print("打粉底【");
        showable.show();
        System.out.print("】");
    }
}

