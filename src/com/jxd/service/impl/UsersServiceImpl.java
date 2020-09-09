package com.jxd.service.impl;

import com.jxd.dao.IUsersDao;
import com.jxd.dao.impl.UsersDaoImpl;
import com.jxd.model.Users;
import com.jxd.service.IUsersService;

import java.util.List;

/**
 * @author Liang Yue
 * @description TODO
 * @date 2020/8/22 20:38
 */
public class UsersServiceImpl implements IUsersService {
    IUsersDao usersDao = new UsersDaoImpl();

    /**
     * 注册
     *
     * @param users 要注册的用户
     * @return 是否注册成功
     */
    @Override
    public boolean register(Users users) {
        return usersDao.register(users);
    }

    /**
     * 登录
     *
     * @param uname 用户名
     * @param pwd   密码
     * @return 是否登陆成功
     */
    @Override
    public boolean login(String uname, String pwd) {
        return usersDao.login(uname, pwd);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }
}
