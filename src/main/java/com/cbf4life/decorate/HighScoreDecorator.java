package com.cbf4life.decorate;

/**
 * @author wujc
 * @ClassName HighScoreDecorator
 * @Description: 我要把我学校的最高成绩告诉老爸
 * @create 2018-08-12 15:53
 */
public class HighScoreDecorator extends Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }
    //我要汇报最高成绩
    private void reportHighScore() {
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    //最高成绩我要老爸看成绩单前告诉他，否则等他看一下，就抡起笤帚揍我，我那还有机会说啊
    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
