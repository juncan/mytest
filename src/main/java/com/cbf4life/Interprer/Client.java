package com.cbf4life.Interprer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-11 11:36
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String expStr = getExpStr();

        //赋值
        HashMap<String, Integer> var = getValue(expStr);

        Calculator cal = new Calculator(expStr);

        System.out.println("运算结果为：" + expStr + "=" + cal.run(var));
    }

    //获得值映射
    private static HashMap<String, Integer> getValue(String expStr) throws IOException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //解析有几个参数要传递
        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                if (!map.containsKey(String.valueOf(ch))) { //解决重复参赛问题
                    System.out.println("请输入" + ch + "的值");
                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                }
            }
        }
        return map;
    }

    //获得表达式
    public static String getExpStr() throws IOException {
        System.out.println("请输入表达式：");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }
}
