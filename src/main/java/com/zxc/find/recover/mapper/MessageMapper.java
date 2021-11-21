package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Message;

/**
 * @author Miyam
 */
public interface MessageMapper extends BaseMapper<Message> {
    public Message selectMessageByFindId(Integer id);
}
