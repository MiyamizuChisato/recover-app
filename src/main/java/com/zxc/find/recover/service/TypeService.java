package com.zxc.find.recover.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.utils.Response;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Miyam
 */
public interface TypeService {
    public List<Type> getAllType();
    
    public int addType(Type type);

    public int updateType(Type type);

    public int deleteType(Integer id);
}
