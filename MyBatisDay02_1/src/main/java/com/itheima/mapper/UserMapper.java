package com.itheima.mapper;

import com.itheima.pojo.QueryVo;
import com.itheima.pojo.User;

import java.util.List;

public interface UserMapper {
    //根据id查询用户
    public User queryUserById01(Integer id);

    //根据用户名模糊查询用户数据,演示包装pojo的使用
    public List<User> queryUserByIds(QueryVo queryVo);


}
