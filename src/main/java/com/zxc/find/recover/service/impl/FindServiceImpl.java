package com.zxc.find.recover.service.impl;

import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.mapper.FindMapper;
import com.zxc.find.recover.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Miyam
 */
@Service
@Transactional
public class FindServiceImpl implements FindService {
    @Autowired
    private FindMapper mapper;

    @Override
    public List<Find> getFindIndex() {
        return mapper.selectFindsForIndex();
    }

    @Override
    public Find getFindById(Integer id) {
        return mapper.selectFindById(id);
    }
}
