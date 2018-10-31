package com.cbf4life.Composite;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName IBranch
 * @Description: 树枝节点，也就是各个部门经理和组长角色
 * @create 2018-08-12 19:51
 */
public interface IBranch {
    //能够增加小兵（树叶节点）或者是经理（树枝节点）
    public void addSubordinate(ICorp corp);

    //获得下级信息
    public ArrayList<ICorp> getSubordinateInfo();
}
