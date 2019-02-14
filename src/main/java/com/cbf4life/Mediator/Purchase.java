package com.cbf4life.Mediator;

/**
 * @author wujc
 * @ClassName Purchase
 * @Description: TODO
 * @create 2018-11-10 16:00
 */
public class Purchase extends AbstractColleague{
    public Purchase(AbstractMediator _mediator) {
        super(_mediator);
    }

    //采购INM型号的电脑
    public void buyIBMcomputer(int number) {

        super.mediator.execute("purchase.buy",number);
    }

    //不再采购电脑
    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }

}
