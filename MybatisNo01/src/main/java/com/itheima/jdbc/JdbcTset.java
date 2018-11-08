package com.itheima.jdbc;

import java.sql.*;

//使用jdbc技术操作数据库,原生六步骤
public class JdbcTset {
    public static void main(String[] args) {

        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatisdb","root","pznsh");
            //设置sql语句
            String sql = "select * from user where username like ?;";
            //获取statement执行者对象
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,"%王%");
            //执行sql查询语句
            resultSet = preparedStatement.executeQuery();
            //操作/解析查询结果
            while (resultSet.next()){
                System.out.println(resultSet.getString("id") + ":" + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement !=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
