package com.cbf4life.proxy;

/**
 * @author wujc
 * @ClassName WangPo
 * @Description: 王婆这个人老聪明了，他太老了，是个男人都看不上
 * 但是她智慧有经验呀，她座位一类女人的代理
 * @create 2018-07-15 11:01
 */
public class WangPo implements KindWomen{
    private KindWomen kindWomen;

    public WangPo() { //默认的话，是潘金莲的代理
        this.kindWomen = new PanJinLian();
    }

    //她可以是KindWomen的任何一个女人的代理，只是你是这一类型
    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    @Override
    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan(); //王婆这么大年纪了，谁看她抛媚眼？！
    }

    @Override
    public void happyWithMan() {
        this.kindWomen.happyWithMan(); //自己老了，干不了，可以让年轻的代替
    }
}
