package com.cbf4life.decorate.paint;

/**
 * @author xingkong
 * @date 2023/3/28 9:51
 */
public abstract class Decorator implements Showable {

    protected Showable showable;

    public Decorator(Showable showable) {
        this.showable = showable;
    }

    @Override
    public void show() {
        showable.show();//直接调用不加任何装饰
    }

}
