package com.cbf4life.abstractfactory.attack;

/**
 * 初级兵种类
 *
 * @author xingkong
 * @date 2023/3/24 11:44
 */
public abstract class LowClassUnit extends Unit{

    public LowClassUnit(int x, int y) {
        super(5, 2, 35, x, y);
    }
}
