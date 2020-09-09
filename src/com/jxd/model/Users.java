package com.jxd.model;

/**
 * @author Liang Yue
 * @description TODO 用户类
 * @date 2020/8/22 20:18
 */
public class Users {
    private String uname;//用户名
    private String pwd;//密码
    private String email;//邮箱

    public Users() {
    }

    public Users(String uname, String pwd, String email) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
