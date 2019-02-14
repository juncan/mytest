package com.cbf4life.Mediator;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-10 16:29
 */
public class Client {
    public static void main(String[] args) {
        AbstractMediator mediator = new Mediator();
        //采购人员采购电脑
        System.out.println("------采购人员采购电脑------");
        Purchase purchaase = new Purchase(mediator);
        purchaase.buyIBMcomputer(100);

        //销售人员销售电脑
        System.out.println("\n------销售人员销售电脑------");

        Sale sale = new Sale(mediator);
        sale.sellIBMComputer(1);

        //库房管理人员管理库存
        System.out.println("\n-----库房管理人员清理库存------");
        Stock stock = new Stock(mediator);
        stock.clearStock();

    }
}
