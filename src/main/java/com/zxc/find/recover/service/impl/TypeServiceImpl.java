package com.zxc.find.recover.service.impl;

import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.mapper.TypeMapper;
import com.zxc.find.recover.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miyam
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper mapper;

    @Override
    public List<Type> getAllType() {
        return mapper.selectList(null);
    }
}
