package com.cbf4life.decorate;

/**
 * @author wujc
 * @ClassName SchoolReport
 * @Description: 成绩单的抽象类
 * @create 2018-08-12 15:28
 */
public abstract class SchoolReport {
    //成绩单的主要展示的就是你的成绩情况
    public abstract void report();

    //成绩单要家长签字，这个是最要命的
    public abstract void sign(String name);
}
