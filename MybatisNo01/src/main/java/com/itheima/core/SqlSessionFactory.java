package com.itheima.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class SqlSessionFactory {
    //不需要在这里读取SqlMapConfig.xml,因为有专门的地方进行配置文件的读取 ,这里只需要声明InputStream即可,由其他的类注入即可
    private InputStream inputStream;

    //提供构造方法让其他的类注入配置文件信息即可
    public SqlSessionFactory(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    //创建SqlSession
    public SqlSession openSession(){
        //获取Config
        Config config = getConfig();
        //获取SqlSession
        SqlSession sqlSession = new SqlSession(config);

        //返回SqlSession
        return sqlSession;
    }

    //获取config,里面封装数据库的连接信息
    private Config getConfig() {
        //先声明config
        Config config = new Config();
        //创建SAXReader
        SAXReader saxReader = new SAXReader();

        try {
            //获取Document
            Document docu = saxReader.read(inputStream);
            //获取根节点
            Element rootElement = docu.getRootElement();
            //查询数据库连接的四大属性
            Element driver = (Element)rootElement.selectNodes("//driver").get(0);
            Element url = (Element)rootElement.selectNodes("//url").get(0);
            Element username = (Element)rootElement.selectNodes("//username").get(0);
            Element password = (Element)rootElement.selectNodes("//password").get(0);
            //封装数据到Config中
            config.setDriver(driver.getText());
            config.setUrl(url.getText());
            config.setUsername(username.getText());
            config.setPassword(password.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return config;
    }
}
