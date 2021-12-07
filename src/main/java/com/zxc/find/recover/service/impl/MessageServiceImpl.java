package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/1 20:16
 */
@Component
@Transactional
public class MessageServiceImpl implements CRUDService<Message> {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int addNewInfo(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public int updateInfo(Message message) {
        QueryWrapper<Message> messageWrapper = new QueryWrapper<>();
        messageWrapper.eq("message_id",message.getId());
        return messageMapper.update(message,messageWrapper);
    }

    @Override
    public int deleteInfo(Message message) {
        QueryWrapper<Message> messageWrapper = new QueryWrapper<>();
        messageWrapper.eq("message_id",message.getId());
        return messageMapper.delete(messageWrapper);
    }

    //必须要实现，不能使用抽象类，否则无法运行
    @Override
    public List<Message> getIndex() {
        return null;
    }

    @Override
    public Message getInfoById(Integer id) {
        return null;
    }

    @Override
    public void viewCountPlus(Message entity) {
    }
}
