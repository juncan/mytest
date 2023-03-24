package com.base.abstr;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/2/24 15:21
 */
public class OracleDao extends AbstractDao{
    @Override
    public void connect() {
        System.out.println("oracle数据库连接");
    }
}
