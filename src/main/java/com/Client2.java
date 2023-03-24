package com;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.Sftp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/2/28 15:20
 */
public class Client2 {
    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>();

        for (int i = 0; i < 600000; i++) {
            dataList.add(i+"");
        }
        long start = System.currentTimeMillis();
        dataList.forEach(data->{
            //System.out.println(data);

        });
        long end = System.currentTimeMillis();
        System.out.println("foreach遍历花费的时间：" + (end - start) + "");

        start = System.currentTimeMillis();
        dataList.stream().forEach(data->{
            //System.out.println(data);
            if ("1".equals(data)) {
                return;
            }
            System.out.println(data);
        });
        end = System.currentTimeMillis();
        System.out.println("stream.forEach遍历花费的时间：" + (end - start) + "");


        start = System.currentTimeMillis();
        dataList.parallelStream().forEach(data->{
            //System.out.println(data);
        });
        end = System.currentTimeMillis();
        System.out.println("parallelStream.forEach遍历花费的时间：" + (end - start) + "");

    }
}
