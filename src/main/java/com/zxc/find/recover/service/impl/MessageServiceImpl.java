package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/1 20:16
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int addMessage(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public int updateMessage(Message message) {
        QueryWrapper<Message> messageWrapper = new QueryWrapper<>();
        messageWrapper.eq("message_id",message.getId());
        return messageMapper.update(message,messageWrapper);
    }

    @Override
    public int deleteMessage(Integer id) {
        QueryWrapper<Message> messageWrapper = new QueryWrapper<>();
        messageWrapper.eq("message_id",id);
        return messageMapper.delete(messageWrapper);
    }
}
