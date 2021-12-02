package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.entity.Message;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/2 9:03
 */
public interface MessageService {
    public int addMessage(Message message);

    public int updateMessage(Message message);

    public int deleteMessage(Integer id);
}
