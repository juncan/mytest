package com.cbf4life.chain.change;

import com.cbf4life.chain.Handler;
import com.cbf4life.chain.IWomen;
import com.cbf4life.chain.Women;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-08-26 22:45
 */
public class Client {
    public static void main(String[] args) {
        //随机挑选几个女性
        Random rand = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Women(rand.nextInt(4), "我要出去逛逛"));
        }

        //定义三个请求对象
        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();

        //设置请示顺序
        father.setNext(husband);
        husband.setNext(son);
        for (IWomen women : arrayList) {
            father.HandleMessage(women);
        }
        String str = "00000000010000000002";
        String[] strIds = str.split("");
        for (String id : strIds) {
            System.out.println(id);
        }
    }
}