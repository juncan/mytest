package com.cbf4life.decorate;

/**
 * @author wujc
 * @ClassName SugarFouthGradeSchoolReport
 * @Description: 对这个成绩单进行美化
 * sugar 这个词太好了，名词是糖的意思，动词就是美化
 * 给你颗糖你还不美去
 * @create 2018-08-12 15:40
 */
public class SugarFouthGradeSchoolReport extends FouthGradeSchoolReport {
    //首先要定义你要美化的方法，先给老爸说学校最高成绩
    private void reportHighSccore() {
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    //在老爸看完毕成绩单后，我再汇报学校的排名情况
    private void reportSort() {
        System.out.println("我是排名第38名。。。");
    }

    //由于汇报的内容已发生变更，那所以要重写父类

    @Override
    public void report() {
        this.reportHighSccore();
        super.report();
        this.reportSort();
    }
}
