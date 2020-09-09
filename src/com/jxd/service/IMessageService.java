package com.jxd.service;

import com.jxd.model.Message;

import java.util.List;

public interface IMessageService {
    /**
     * 增加消息
     *
     * @param message 要增加的消息
     * @return 是否增加成功
     */
    boolean addMsg(Message message);

    /**
     * 根据编号修改消息
     *
     * @param message 要修改的消息
     * @return 是否修改成功
     */
    boolean updateMsgByMsgno(Message message);

    /**
     * 根据编号删除消息
     *
     * @param msgno 要删除的消息
     * @return 是否删除成功
     */
    boolean deleteMsgByMsgno(int msgno);

    /**
     * 根据编号获取消息
     *
     * @param msgno 消息编号
     * @return 要获取的消息
     */
    Message showMsg(int msgno);

    /**
     * 根据用户名获取已发未删短消息列表
     *
     * @param uname 用户名
     * @return 短消息列表
     */
    List<Message> getMsgByUname(String uname);

    /**
     * 根据用户名获取收到未删短消息列表
     *
     * @param uname 收件人
     * @return 短消息列表
     */
    List<Message> getMsgByAddressee(String uname);

    /**
     * 获取回收站中的消息
     * @param uname 用户名
     * @return 消息列表
     */
    List<Message> getMsgFromRecycleBin(String uname);
}
