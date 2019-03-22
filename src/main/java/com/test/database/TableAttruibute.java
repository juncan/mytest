package com.test.database;

import java.sql.*;

/**
 * @author wujc
 * @ClassName TableAttruibute
 * @Description: TODO
 * @create 2018-11-29 16:01
 */
public class TableAttruibute {
    public static void main(String[] args) {
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        //String url = "jdbc:mysql://192.168.20.10:3306/gray_dy_kp";
        //String url = "jdbc:mysql://localhost:3306/gray_dy_kp";
        String url = "jdbc:mysql://192.168.20.61:3306/dy_kp";

        //MySQL配置时的用户名
        //String user = "99cj_user";
        //String user = "yoya_school";
        String user = "dy_kp";
        //MySQL配置时的密码
        //String password = "99cj_user_pwd";
        //String password = "y4yrl9d_~!@#$%";
        String password = "kp_dy_yoya_20170807";
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            ResultSet rs = 	con.getMetaData().getTables("","","",null);
            while(rs.next()) {
                String sql = "select * from "+ rs.getString("TABLE_NAME");
                PreparedStatement stmt;
                stmt = con.prepareStatement(sql);
                ResultSet rstable = stmt.executeQuery(sql);
                ResultSetMetaData data = rstable.getMetaData();
                //System.out.println("-----------"+rs.getString("TABLE_NAME")+"----------------");
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);

                    //匹配站点ID的字段及其更新语句
                    /*if((data.getColumnTypeName(i).equalsIgnoreCase("char") || data.getColumnTypeName(i).equalsIgnoreCase("varchar")) &&!"site_id".equalsIgnoreCase(columnName)){
                        String searchSql = "select " + columnName + " from " + rs.getString("TABLE_NAME") + " where " + columnName + " = '5abc504de4b006684a899ce700000000'";
                        stmt = con.prepareStatement(searchSql);
                        rstable = stmt.executeQuery(searchSql);
                        while (rstable.next()) {
                            System.out.println("字段名称："+columnName + "   表名：" + rs.getString("TABLE_NAME"));
                            //System.out.println("update "+rs.getString("TABLE_NAME")+" set "+columnName+" = '94a87ae8f3b911e8b31300505681279f' where site_id ='94a87ae8f3b911e8b31300505681279f' and "+columnName+" ='5a9ca108e4b0cd28f9c8a0c500000000';");
                            break;
                        }
                    }*/

                    if("site_id".equalsIgnoreCase(columnName)){
                        //删除不在具体站点下的sql语句
                        //System.out.println("delete from new_dy_activity."+rs.getString("TABLE_NAME")+" where site_id not in('59b6022e0cf282cdb49be9c000000000','5a9ca108e4b0cd28f9c8a0c500000000','5b8759ffe4b0ce15aab7db3200000000');");
                        //更新站点ID
                        //System.out.println("update new_dy_activity."+rs.getString("TABLE_NAME")+" set site_id = '89bdeb0ef3b911e8b31300505681279f' where site_id ='59b6022e0cf282cdb49be9c000000000';");
                        //System.out.println("update new_dy_activity."+rs.getString("TABLE_NAME")+" set site_id = '8e087689f3b911e8b31300505681279f' where site_id ='5a9ca108e4b0cd28f9c8a0c500000000';");
                        //System.out.println("update new_dy_activity."+rs.getString("TABLE_NAME")+" set site_id = '94a87ae8f3b911e8b31300505681279f' where site_id ='5b8759ffe4b0ce15aab7db3200000000';");
                        System.out.println("update new_dy_kp."+rs.getString("TABLE_NAME")+" set site_id = '254f682df46511e892625800e349b86a' where site_id ='5a9ca108e4b0cd28f9c8a0c500000000';");
                    }
                }
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }
}
