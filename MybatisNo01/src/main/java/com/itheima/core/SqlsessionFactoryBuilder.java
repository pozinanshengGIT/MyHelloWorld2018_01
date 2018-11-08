package com.itheima.core;
//这里是构造者模式
//提供了很多方法来构建SqlsessionFactory
//用户在使用的时候可以很方便的根据当前的环境来选择合适的方法进行构建

import java.io.InputStream;

public class SqlsessionFactoryBuilder {

    //默认的构建方法,因为我们知道配置文件在哪
    public SqlSessionFactory build(){
        InputStream in = SqlsessionFactoryBuilder.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory(in);
        return sqlSessionFactory;
    }

    //指定配置文件的文件名,来获取sqlsessionFactory的方法
    public SqlSessionFactory build2(String resource){
        InputStream inputStream = SqlsessionFactoryBuilder.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory(inputStream);
        return sqlSessionFactory;

    }

}
