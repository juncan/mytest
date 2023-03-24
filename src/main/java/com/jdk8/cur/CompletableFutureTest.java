package com.jdk8.cur;

import java.util.concurrent.CompletableFuture;

/**
 * 以Async结尾的方法，都是异步方法，对应的没有Async则是同步方法，一般都是一个异步方法对应一个同步方法。
 * 以Async后缀结尾的方法，都有两个重载的方法，一个是使用内容的forkjoin线程池，一种是使用自定义线程池
 * 以run开头的方法，其入口参数一定是无参的，并且没有返回值，类似于执行Runnable方法。
 * 以supply开头的方法，入口也是没有参数的，但是有返回值
 * 以Accept开头或者结尾的方法，入口参数是有参数，但是没有返回值
 * 以Apply开头或者结尾的方法，入口有参数，有返回值
 * 带有either后缀的方法，表示谁先完成就消费谁
 *
 * 作者：愤怒的酸菜鱼
 * 链接：https://juejin.cn/post/6844904195162636295
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author xingkong
 * @date 2023/3/1 13:39
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> rice = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始制作米饭，并获得煮熟的米饭");
            return "煮熟的米饭";
        });

        CompletableFuture<String> mike = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始热牛奶，并获得加热的牛奶");
            return "加热的牛奶";
        });

        //我想两个都好了，才吃早饭，thenCombineAsync有入参，有返回值
        mike.thenCombineAsync(rice, (m, r) -> {
            System.out.println("我收获了早饭：" + m + "," + r);
            return m + r;
        });

        //有入参，无返回值
        mike.thenAcceptBothAsync(rice, (m, r) -> {
            System.out.println("我收获了早饭：" + m + "," + r);
        });

        //无入参，入参
        mike.runAfterBothAsync(rice, () -> {
            System.out.println("我收获了早餐");
        });

        //或者直接连接两个CompletableFuture
        rice.thenComposeAsync(r -> CompletableFuture.supplyAsync(() -> {
            System.out.println("开始煮牛奶");
            System.out.println("同时开始煮米饭");
            return "mike";
        }));

    }
}
