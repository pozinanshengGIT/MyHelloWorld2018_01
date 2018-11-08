package com.itheima.core;

import com.itheima.pojo.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//这里要做的事,获取sql语句,实现查询所有数据
//就是对jdbc进行封装
public class SqlSession {

    //声明一个数据库连接信息的config
    private Config config;

    public SqlSession(Config config) {
        this.config = config;
    }

    public List<User> selectList(){
        //声明要返回的结果List集合
        List<User> list = new ArrayList<User>();

        //原生jdbc  6步骤
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //1,注册驱动
            Class.forName(config.getDriver());
            //2,获取连接对象
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());

            //3,读取User.xml配置文件,获取需要执行的sql语句
              //读取User.xml配置文件
            InputStream resourceAsStream = SqlSession.class.getClassLoader().getResourceAsStream("User.xml");
              //声明SAXreder解析xml文件
            SAXReader saxReader = new SAXReader();
              //读取配置文件,获取document对象
            Document read = saxReader.read(resourceAsStream);
              //获取根节点
            Element rootElement = read.getRootElement();
              //获取select标签
            Element element = (Element) rootElement.selectNodes("//select").get(0);
              //获取sql语句
            String sql = element.getText();


            //4,获取执行者对象statement
            preparedStatement = connection.prepareStatement(sql);

            //5,不用设置参数
            //6,执行statement,获取查询结果集合ResultSet
            resultSet = preparedStatement.executeQuery();

            //7,解析结果集,创建User对象封装数据,并把User放到list集合中
            while (resultSet.next()){
                //创建User对象
                User user = new User();
                //把结果集中的数据封装到User对象中
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setAddress(resultSet.getString("address"));
                //把封装好的Usre放到list集合中
                list.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //8,释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
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

        return list;



    }

}
