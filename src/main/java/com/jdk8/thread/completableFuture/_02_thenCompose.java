package com.jdk8.thread.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author xingkong
 * @date 2022/9/10 12:01
 */
public class _02_thenCompose {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进去餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋+一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(100);
            return dish + "米饭";
        }));
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", cf1.join()));
    }
}
