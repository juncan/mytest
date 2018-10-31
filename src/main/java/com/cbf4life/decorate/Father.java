package com.cbf4life.decorate;

/**
 * @author wujc
 * @ClassName Father
 * @Description: 老爸看成绩单
 * @create 2018-08-12 15:37
 */
public class Father {
    public static void main(String[] args) {
       /* //成绩单拿过来
        SchoolReport srr = new FouthGradeSchoolReport();
        //看成绩单
        srr.report();

        //签名？休想*/

       /*//美化过的成绩单拿过来
        SchoolReport sr = new SugarFouthGradeSchoolReport();

        //看成绩单
        sr.report();

        //然后老爸，一看，很开心，就签名了
        sr.sign("老三");*/

       //成绩单拿过来
        SchoolReport sr;

        sr = new FouthGradeSchoolReport();

        //加了最高分说明的成绩单
        sr = new HighScoreDecorator(sr);

        //又加了成绩排名的情况
        sr = new SortDecorator(sr);

        //看成绩单
        sr.report();

        //然后老爸一看，很开心，就签名了
        sr.sign("老三");
    }
}
