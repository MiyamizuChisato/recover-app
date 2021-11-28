package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Lost;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:39
 */
@Repository
public interface LostMapper extends BaseMapper<Lost> {
    public List<Lost> selectLostForIndex();
    public Lost selectLostById(Integer id);
}
