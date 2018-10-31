package com.cbf4life.Composite;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName IRoot
 * @Description: 定义一个根节点，就为总经理服务
 * @create 2018-08-12 19:40
 */
public interface IRoot {
    //得到总经理的信息
    public String getInfo();

    //总经理下边要有小兵，那要能增加小兵，比如研发部总经理，这是个树枝节点
    public void add(IBranch branch);

    //那要能增加树叶节点
    public void add(ILeaf leaf);

    //既然能增加，那还要能够遍历，不可能总经理不知道他手下有多少人
    public ArrayList getSubordinateInfo();
}
