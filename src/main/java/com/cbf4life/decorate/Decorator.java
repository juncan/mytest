package com.cbf4life.decorate;


/**
 * @author wujc
 * @ClassName Decorator
 * @Description: 装饰类，我要把我的成绩单装饰下
 * @create 2018-08-12 15:48
 */
public abstract class Decorator extends SchoolReport {
    //首先我要知道是哪个成绩单
    private SchoolReport sr;

    //构造函数，传递成绩过来
    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }

    //成绩单还是要被看到的
    @Override
    public void report() {
        this.sr.report();
    }

    //看完毕还是要签名的
    @Override
    public void sign(String name) {
        this.sr.sign(name);
    }

}
