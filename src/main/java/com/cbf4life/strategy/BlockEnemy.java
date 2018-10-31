package com.cbf4life.strategy;

/**
 * @author wujc
 * @ClassName BlockEnemy
 * @Description: 孙夫人断后，挡住追兵
 * @create 2018-07-15 10:35
 */
public class BlockEnemy implements Istrategy{
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
