package com.cbf4life.facade.cook;


/**
 * @author xingkong
 * @date 2023/3/27 11:28
 */
public class Facade {

    private VegVendor vegVendor;
    private Chef chef;
    private Waiter waiter;
    private Cleaner cleaner;

    public Facade() {
        this.vegVendor = new VegVendor();
        //开门前就找蔬菜商准备好蔬菜
        vegVendor.purchase();
        //雇佣厨师
        this.chef = new Chef();
        //雇佣服务员
        this.waiter = new Waiter();
        //雇佣清洁工、洗碗工等
        this.cleaner = new Cleaner();
    }

    public void order() {
        //接待，入座，点菜
        waiter.order();
        //找厨师做饭
        chef.cook();
        //上菜
        waiter.serve();
        //收拾桌子，洗碗，以及其他操作
        cleaner.clean();
        cleaner.wash();
    }
}

