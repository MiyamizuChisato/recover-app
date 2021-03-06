package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Find;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Miyam
 */
@Repository
public interface FindMapper extends BaseMapper<Find> {
    public List<Find> selectFindsForIndex();

    public Find selectFindById(Integer id);
}
