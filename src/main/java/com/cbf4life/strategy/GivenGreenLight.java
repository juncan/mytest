package com.cbf4life.strategy;

/**
 * @author wujc
 * @ClassName GivenGreenLight
 * @Description: 求吴国太开个绿灯
 * @create 2018-07-15 10:34
 */
public class GivenGreenLight implements Istrategy{
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
