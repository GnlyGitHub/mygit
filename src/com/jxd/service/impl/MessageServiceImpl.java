package com.jxd.service.impl;

import com.jxd.dao.IMessageDao;
import com.jxd.dao.impl.MessageDaoImpl;
import com.jxd.model.Message;
import com.jxd.service.IMessageService;

import java.util.List;

/**
 * @author Liang Yue
 * @description TODO
 * @date 2020/8/22 20:39
 */
public class MessageServiceImpl implements IMessageService {
    IMessageDao messageDao = new MessageDaoImpl();

    /**
     * 增加消息
     *
     * @param message 要增加的消息
     * @return 是否增加成功
     */
    @Override
    public boolean addMsg(Message message) {
        return messageDao.addMsg(message);
    }

    /**
     * 根据编号修改消息
     *
     * @param message 要修改的消息
     * @return 是否修改成功
     */
    @Override
    public boolean updateMsgByMsgno(Message message) {
        return messageDao.updateMsgByMsgno(message);
    }

    /**
     * 根据编号删除消息
     *
     * @param msgno 要删除的消息
     * @return 是否删除成功
     */
    @Override
    public boolean deleteMsgByMsgno(int msgno) {
        return messageDao.deleteMsgByMsgno(msgno);
    }

    /**
     * 根据编号获取消息
     *
     * @param msgno 消息编号
     * @return 要获取的消息
     */
    @Override
    public Message showMsg(int msgno) {
        return messageDao.showMsg(msgno);
    }

    /**
     * 根据用户名获取已发未删短消息列表
     *
     * @param uname 用户名
     * @return 短消息列表
     */
    @Override
    public List<Message> getMsgByUname(String uname) {
        return messageDao.getMsgByUname(uname);
    }

    /**
     * 根据用户名获取收到未删短消息列表
     *
     * @param uname 收件人
     * @return 短消息列表
     */
    @Override
    public List<Message> getMsgByAddressee(String uname) {
        return messageDao.getMsgByAddressee(uname);
    }

    /**
     * 获取回收站中的消息
     *
     * @param uname 用户名
     * @return 消息列表
     */
    @Override
    public List<Message> getMsgFromRecycleBin(String uname) {
        return messageDao.getMsgFromRecycleBin(uname);
    }
}
