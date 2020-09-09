package com.jxd.dao;

import com.jxd.model.Users;

import java.util.List;

public interface IUsersDao {
    /**
     * 注册
     * @param users 要注册的用户
     * @return 是否注册成功
     */
    boolean register(Users users);

    /**
     * 登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 是否登陆成功
     */
    boolean login(String uname,String pwd);

    /**
     * 获取所有用户名
     * @return 用户名列表
     */
    List<Users> getAllUsers();
}
