package Test;

import com.itheima.dao.Userdao;
import com.itheima.dao.UserdaoImpl;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class UserdaoTest {
    @Test
    public void test01()throws Exception {
        //创建者对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //读取核心配置文件获取InputStream输入流
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //用创建者对象根据输入流inputStream获取工厂对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);


        //创建dao实例对象
        Userdao userdao = new UserdaoImpl(sqlSessionFactory);
        //根据id  12查询数据库
        User user = userdao.queryUserById(12);
        System.out.println(user);
    }
}
