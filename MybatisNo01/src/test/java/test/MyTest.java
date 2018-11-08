package test;

import com.itheima.core.SqlSession;
import com.itheima.core.SqlSessionFactory;
import com.itheima.core.SqlsessionFactoryBuilder;
import com.itheima.pojo.User;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test01(){
        //s获取构造者
        SqlsessionFactoryBuilder builder = new SqlsessionFactoryBuilder();

        //获取工厂
        SqlSessionFactory sqlSessionFactory = builder.build();
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //使用SqlSessoin操作数据库
        List<User> list = sqlSession.selectList();
        //打印结果
        for (User user : list) {
            System.out.println(user);
        }

    }
}
