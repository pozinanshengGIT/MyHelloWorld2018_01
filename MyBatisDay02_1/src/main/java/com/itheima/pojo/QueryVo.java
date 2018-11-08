package com.itheima.pojo;

import com.itheima.pojo.User;

public class QueryVo {
    private User user;

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QueryVo() {

    }

    public QueryVo(User user) {

        this.user = user;
    }
}
