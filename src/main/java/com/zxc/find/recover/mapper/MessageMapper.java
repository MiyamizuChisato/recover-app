package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Message;

import java.util.List;

/**
 * @author Miyam
 */
public interface MessageMapper extends BaseMapper<Message> {
    public List<Message> selectMessageByFindId(Integer id);
    public List<Message> selectMessageByLostId(Integer id);
}
