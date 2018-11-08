package com.itheima.dao;

import com.itheima.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserdaoImpl implements Userdao {
    private SqlSessionFactory sqlSessionFactory;

    public UserdaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User queryUserById(Integer id) {
        //1,获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2,执行方法操作数据库
        User user = sqlSession.selectOne("test02.selectUserById", id);
        //关闭资源
        sqlSession.close();
        //返回结果
        return user;
    }
}
