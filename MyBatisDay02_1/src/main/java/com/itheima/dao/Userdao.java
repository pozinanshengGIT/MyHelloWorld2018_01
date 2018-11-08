package com.itheima.dao;

import com.itheima.pojo.User;

public interface Userdao {
    //根据id查询用户
    public User queryUserById(Integer id);
}
