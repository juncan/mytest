package com.cbf4life.observer.change;

import com.cbf4life.observer.IHanFeiZi;
import com.cbf4life.observer.ILiSi;
import com.cbf4life.observer.LiSi;

/**
 * @author wujc
 * @ClassName HanFeizi
 * @Description: 韩非子，李斯的师弟，韩国的重要人物
 * @create 2018-08-26 10:35
 */
public class HanFeizi implements IHanFeiZi {
    //把李斯声明出来
    private ILiSi liSi = new LiSi();

    @Override
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了。。。");
        //通知李斯
        this.liSi.update("韩非子在吃饭");
    }

    //韩非子开始娱乐了，古代人没啥娱乐，你能想到的就那么多
    @Override
    public void haveFun() {
        System.out.println("韩非子：开始娱乐了。。。");
        this.liSi.update("韩非子在娱乐");

    }
}
