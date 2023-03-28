package com.cbf4life.decorate.paint;

/**
 * @author xingkong
 * @date 2023/3/28 10:05
 */
public class Lipstick extends Decorator {

    public Lipstick(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("涂口红【");
        showable.show();
        System.out.print("】");
    }
}

