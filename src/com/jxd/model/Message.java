package com.jxd.model;

/**
 * @author Liang Yue
 * @description TODO
 * @date 2020/8/22 20:19
 */
public class Message {
    private int msgno;//编号
    private String uname;//用户名
    private String title;//标题
    private String content;//内容
    private String time;//发送时间
    private int read;//是否已读
    private int del;//是否已删除
    private String addressee;//收件人
    private String fromer;//发件人

    public Message() {
    }

    public Message(int msgno,int read, int del) {
        this.msgno = msgno;
        this.read = read;
        this.del = del;
    }

    public Message(String uname, String title, String content, String addressee) {
        this.uname = uname;
        this.title = title;
        this.content = content;
        this.addressee = addressee;
    }

    public Message(String uname, String title, String content, String addressee, String fromer) {
        this.uname = uname;
        this.title = title;
        this.content = content;
        this.addressee = addressee;
        this.fromer = fromer;
    }

    public Message(int msgno, String uname, String title, String content, String time, int read, int del, String addressee, String fromer) {
        this.msgno = msgno;
        this.uname = uname;
        this.title = title;
        this.content = content;
        this.time = time;
        this.read = read;
        this.del = del;
        this.addressee = addressee;
        this.fromer = fromer;
    }

    public String getFromer() {
        return fromer;
    }

    public void setFromer(String fromer) {
        this.fromer = fromer;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public int getMsgno() {
        return msgno;
    }

    public void setMsgno(int msgno) {
        this.msgno = msgno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
