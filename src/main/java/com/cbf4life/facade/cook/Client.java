package com.cbf4life.facade.cook;

/**
 * @author xingkong
 * @date 2023/3/27 11:26
 */
public class Client {

    public void eat() {
        System.out.println("开始用餐……");
    }

    public void wash() {
        System.out.println("洗碗……");
    }

    public static void main(String[] args) {
        //找蔬菜商买菜
        VegVendor vegVendor = new VegVendor();
        vegVendor.purchase();
        //找妹妹下厨
        Helper sister = new Helper();
        sister.cook();
        //客户端用餐
        Client client = new Client();
        client.eat();
        //最后还得洗碗，确实有点麻烦
        client.wash();
    }
}
