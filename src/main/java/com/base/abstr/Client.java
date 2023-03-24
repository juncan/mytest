package com.base.abstr;

import java.util.Scanner;

/**
 * @author xingkong
 * @date 2023/2/24 15:22
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        IDao dao;
        if (n == 1) {
            dao = new MysqlDao();
        } else {
            dao = new OracleDao();
        }

        dao.connect();

        dao.add();

    }
}
