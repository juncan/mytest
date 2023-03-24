package com.base.abstr;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/2/24 15:21
 */
public class MysqlDao extends AbstractDao{
    @Override
    public void connect() {
        System.out.println("mysql数据库连接");
    }
}
