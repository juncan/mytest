package com.base.abstr;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/2/24 15:18
 */
public abstract class AbstractDao implements IDao {
    @Override
    public void add() {
        System.out.println("新增");
    }

    @Override
    public void del() {
        System.out.println("删除");
    }

    @Override
    public void update() {
        System.out.println("更新");
    }

    @Override
    public void set() {
        System.out.println("修改");
    }
}
