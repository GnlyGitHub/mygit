package com.jxd.dao.impl;

import com.jxd.dao.IMessageDao;
import com.jxd.model.Message;
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
 * @date 2020/8/22 20:36
 */
public class MessageDaoImpl implements IMessageDao {
    /**
     * 增加消息
     *
     * @param message 要增加的消息
     * @return 是否增加成功
     */
    @Override
    public boolean addMsg(Message message) {
        boolean isAdd = false;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "insert into message(uname,title,content,time,readed,del,addressee,fromer) values(?,?,?,now(),1,1,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getUname());
            preparedStatement.setString(2, message.getTitle());
            preparedStatement.setString(3, message.getContent());
            preparedStatement.setString(4, message.getAddressee());
            preparedStatement.setString(5,message.getFromer());
            int num1 = preparedStatement.executeUpdate();

            preparedStatement.setString(1, message.getAddressee());
            preparedStatement.setString(2, message.getTitle());
            preparedStatement.setString(3, message.getContent());
            preparedStatement.setString(4, message.getAddressee());
            preparedStatement.setString(5,message.getFromer());
            int num2 = preparedStatement.executeUpdate();

            if (num1 == 1 && num2 == 1) {
                isAdd = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null, preparedStatement, connection);
        }
        return isAdd;
    }

    /**
     * 根据编号修改消息
     *
     * @param message 要修改的消息
     * @return 是否修改成功
     */
    @Override
    public boolean updateMsgByMsgno(Message message) {
        boolean isUpdate = false;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "update message set readed=?,del=? where msgno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getRead());
            preparedStatement.setInt(2, message.getDel());
            preparedStatement.setInt(3,message.getMsgno());

            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                isUpdate = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null, preparedStatement, connection);
        }
        return isUpdate;
    }

    /**
     * 根据编号删除消息
     *
     * @param msgno 要删除的消息
     * @return 是否删除成功
     */
    @Override
    public boolean deleteMsgByMsgno(int msgno) {
        boolean isDel = false;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "delete from message where msgno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, msgno);

            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                isDel = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null, preparedStatement, connection);
        }
        return isDel;
    }

    /**
     * 根据编号获取消息
     *
     * @param msgno 消息编号
     * @return 要获取的消息
     */
    @Override
    public Message showMsg(int msgno) {
        Message message = new Message();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select msgno,uname,title,content,time,readed,del,addressee,fromer from message where msgno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, msgno);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message.setMsgno(resultSet.getInt("msgno"));
                message.setUname(resultSet.getString("uname"));
                message.setTitle(resultSet.getString("title"));
                message.setContent(resultSet.getString("content"));
                message.setTime(resultSet.getString("time"));
                message.setRead(resultSet.getInt("readed"));
                message.setDel(resultSet.getInt("del"));
                message.setAddressee(resultSet.getString("addressee"));
                message.setFromer(resultSet.getString("fromer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return message;
    }

    /**
     * 根据用户名获取已发未删短消息列表
     *
     * @param uname 用户名
     * @return 短消息列表
     */
    @Override
    public List<Message> getMsgByUname(String uname) {
        List<Message> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select msgno,title,content,time,readed,del,addressee,fromer from message where uname=? and fromer=? and del=1 order by time desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2,uname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMsgno(resultSet.getInt("msgno"));
                message.setUname(uname);
                message.setTitle(resultSet.getString("title"));
                message.setContent(resultSet.getString("content"));
                message.setTime(resultSet.getString("time"));
                message.setRead(resultSet.getInt("readed"));
                message.setDel(resultSet.getInt("del"));
                message.setAddressee(resultSet.getString("addressee"));
                message.setFromer(resultSet.getString("fromer"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    /**
     * 根据用户名获取收到未删短消息列表
     *
     * @param uname 收件人
     * @return 短消息列表
     */
    @Override
    public List<Message> getMsgByAddressee(String uname) {
        List<Message> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select msgno,uname,title,content,time,readed,del,fromer from message where uname=? and addressee=? and del=1 order by time desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2,uname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMsgno(resultSet.getInt("msgno"));
                message.setUname(resultSet.getString("uname"));
                message.setTitle(resultSet.getString("title"));
                message.setContent(resultSet.getString("content"));
                message.setTime(resultSet.getString("time"));
                message.setRead(resultSet.getInt("readed"));
                message.setDel(resultSet.getInt("del"));
                message.setAddressee(uname);
                message.setFromer(resultSet.getString("fromer"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    /**
     * 获取回收站中的消息
     * @param uname 用户名
     * @return 消息列表
     */
    @Override
    public List<Message> getMsgFromRecycleBin(String uname) {
        List<Message> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select msgno,uname,title,content,time,readed,del,addressee,fromer from message where uname=? and del=0 order by time desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMsgno(resultSet.getInt("msgno"));
                message.setUname(resultSet.getString("uname"));
                message.setTitle(resultSet.getString("title"));
                message.setContent(resultSet.getString("content"));
                message.setTime(resultSet.getString("time"));
                message.setRead(resultSet.getInt("readed"));
                message.setDel(resultSet.getInt("del"));
                message.setAddressee(resultSet.getString("addressee"));
                message.setFromer(resultSet.getString("fromer"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }
}
