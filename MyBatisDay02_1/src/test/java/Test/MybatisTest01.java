package Test;

import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

//用来测试框架
public class MybatisTest01 {
    SqlSessionFactory sqlSessionFactory =null;
    @Before
    public void into() throws Exception {
        //1,创建Builder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2,读取配置文件SqlMapConfig.xml
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //3,使用builder获取工厂对象SqlSessionFactory
       sqlSessionFactory = builder.build(inputStream);
    }
    @Test
    public void test() throws Exception {
        /* //1,创建Builder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2,读取配置文件SqlMapConfig.xml
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //3,使用builder获取工厂对象SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
       */

        //4,使用工厂SqlSessionFactory获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5,使用SqlSession对象中的方法操作数据库
            //selectOne(参数1,参数2);
            //第一个参数是sql语句的id(UserMapper.xml映射文件中的sql语句的id)
            //第二个参数是查询条件数据,这里是数据表user中的字段id
        User user = sqlSession.selectOne("test.queryUserById", 1);
        //6,打印结果(操作结果)
        System.out.println(user);
        //7,释放资源
        sqlSession.close();

    }

    @Test
    public void test02() throws Exception {
  /*    //1,创建造物者对象SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2,读取核心配置文件SqlMapConfig.xml
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //3,使用造物者对象builder获取工厂对像SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
  */
        //4,使用工厂对象sqlSessionFactory获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5,使用sqlSession操作数据库
        List<User> list = sqlSession.selectList("queryUserByUsername", "%王%");
        //6,操作结果
        for (User user : list) {
            System.out.println(user);
        }

        //7,释放资源
        sqlSession.close();

    }

    @Test
    public void test03(){
        //4,使用工厂对象sqlSessionFactory获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5,使用sqlSession操作数据库
        List<User> list = sqlSession.selectList("queryUserByUsername2", "王");
        //6,操作结果
        for (User user : list) {
            System.out.println(user);
        }

        //7,释放资源
        sqlSession.close();

    }

    @Test
    public void test04(){
        //4,使用工厂对象sqlSessionFactory获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建一个User对象,用来获取要存入的数据
        User user = new User();
        user.setUsername("张三三");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京朝阳");
        //使用sqlSession操作数据库
        sqlSession.insert("saveUser", user);
        System.out.println(user);

        //给数据库新增数据,现需要提交事物,才能把数据添加成功,要不就会回滚事物了
        sqlSession.commit();


        //7,释放资源
        sqlSession.close();

    }
    @Test
    public void test05() {
        //4,使用工厂对象sqlSessionFactory获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建一个User对象,用来获取要存入的数据
        User user = new User();
        user.setId(15);
        user.setUsername("周八儿");

        //使用sqlSession操作数据库
        sqlSession.update("updateUserById", user);
        System.out.println(user);

        //给数据更改数据,现需要提交事物,才能把数据更改成功,要不就会回滚事物了
        sqlSession.commit();


        //7,释放资源
        sqlSession.close();
    }

    @Test
    public void test06() {
        //4,使用工厂对象sqlSessionFactory获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建一个User对象,用来获取要存入的数据
        User user = new User();
        user.setId(12);


        //使用sqlSession操作数据库
        sqlSession.delete("deleteUserById", user);
        System.out.println(user);

        //给数据更改数据,现需要提交事物,才能把数据更改成功,要不就会回滚事物了
        sqlSession.commit();

        //7,释放资源
        sqlSession.close();
    }
}

