package com.jxd.dao.impl;

import com.jxd.dao.IUsersDao;
import com.jxd.model.Users;
import com.jxd.uitl.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liang Yue
 * @description TODO
 * @date 2020/8/22 20:37
 */
public class UsersDaoImpl implements IUsersDao {
    /**
     * 注册
     * @param users 要注册的用户
     * @return 是否注册成功
     */
    @Override
    public boolean register(Users users) {
        boolean idRegister = false;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try{
            //定义sql
            String sql = "insert into users(uname,pwd,email) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,users.getUname());
            preparedStatement.setString(2,users.getPwd());
            preparedStatement.setString(3,users.getEmail());

            //执行sql
            int num = preparedStatement.executeUpdate();
            if (num == 1){
                idRegister = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null, preparedStatement, connection);
        }
        return idRegister;
    }

    /**
     * 登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 是否登陆成功
     */
    @Override
    public boolean login(String uname, String pwd) {
        boolean isLogin = false;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql = "select uname from users where uname=? and pwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uname);
            preparedStatement.setString(2,pwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                isLogin = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return isLogin;
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql = "select uname,pwd,email from users";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Users users = new Users();
                users.setUname(resultSet.getString("uname"));
                users.setPwd(resultSet.getString("pwd"));
                users.setEmail(resultSet.getString("email"));
                list.add(users);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }
}
