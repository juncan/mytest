package com.test.database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wujc
 * @ClassName AnalysisDatabaseStructure
 * @Description: TODO
 * @create 2018-11-28 20:14
 */
public class AnalysisDatabaseStructure {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, IOException {
        Connection conn = null;
        Statement st = null;
        String fileUrl = "C:\\Users\\Administrator\\Desktop\\河北正式数据";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/dy_kp", "yoya_school", "y4yrl9d_~!@#$%");
        // 1、获取数据库所有表
        StringBuffer sbTables = new StringBuffer();
        List<String> tables = new ArrayList<String>();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2、遍历数据库表，获取各表的字段等信息
        StringBuffer sbCloumns = new StringBuffer();
        for (String tableName : tables) {
            String sql = "select * from " + tableName;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                sbCloumns.append("INSERT INTO newTable."+tableName+" SELECT * FROM oldTable."+tableName+" WHERE "+meta.getColumnName(1)+" NOT IN(SELECT "+meta.getColumnName(1)+" FROM newTable."+tableName+");\r\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //写数据到文件中
        FileWriter writer;
        try {
            writer = new FileWriter("E:/dy_kp.txt");
            writer.write(sbCloumns.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("输入完成");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
