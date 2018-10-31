package com.cbf4life.Composite;

/**
 * @author wujc
 * @ClassName Leaf
 * @Description: 最小的叶子节点
 * @create 2018-08-12 20:00
 */
public class Leaf implements ICorp{
    //叶子叫什么名字
    private String name = "";
    //叶子的职位
    private String position = "";
    //叶子的薪水
    private int salary = 0;


    public Leaf(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    //最小的小兵只能获得自己的信息了
    @Override
    public String getInfo() {
        String info = "";
        info = info + "/t职位：" + this.position;
        info = info + "/t薪水：" + this.salary;
        return info;
    }

}
