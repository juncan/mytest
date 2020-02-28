package com.algorithm;

/**
 * @author wujc
 * @ClassName jitu
 * @Description: 鸡兔
 */
public class jitu {
    public static void main(String[] args) {
        solution(87, 90);
    }

    public static void solution(int x,int y){
        if (x == 0) {
            System.out.println("头不能为0个");
            return;
        }
        if (y % 2 != 0) {
            System.out.println("输入的腿必须为偶数个");
            return;
        }
        boolean flag = false;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= x; j++) {
                if (2 * i + 4 * j == y  && i+j == x) {
                    System.out.println(i + " " + j);
                    flag = true;
                }
            }
        }
        if(!flag)
        System.out.println("输入的不能组成具体的鸡和兔的个数");
    }
}
