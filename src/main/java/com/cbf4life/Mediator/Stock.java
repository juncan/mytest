package com.cbf4life.Mediator;

/**
 * @author wujc
 * @ClassName Stock
 * @Description: TODO
 * @create 2018-11-10 16:06
 */
public class Stock extends AbstractColleague {
    //刚开始有100台电脑
    private static int COMPUTER_NUMBER = 100;

    public Stock(AbstractMediator _mediator) {
        super(_mediator);
    }

    //库存增加
    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);

    }

    //库存降低
    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);
    }

    //获得库存数量
    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    //存货压力大了，就通知采购人员不要采购,销售人员要尽快销售
    public void clearStock() {
        System.out.println("清理存货数量为：" + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}
