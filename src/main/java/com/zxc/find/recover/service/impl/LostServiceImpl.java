package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.LostMapper;
import com.zxc.find.recover.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:40
 */
@Service
@Transactional
public class LostServiceImpl implements LostService {

    @Autowired
    private LostMapper lostMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Lost> getLostIndex() {
        return lostMapper.selectLostForIndex();
    }

    @Override
    public Lost getLostById(Integer id) {
        Lost lost = lostMapper.selectLostById(id);
        if (lost != null){
            viewCountPlus(lost);
        }
        return lost;
    }

    @Override
    public int updateLost(Lost lost) {
        QueryWrapper<Lost> wrapper = new QueryWrapper<>();
        wrapper.eq("lost_id", lost.getId());
        return lostMapper.update(lost, wrapper);
    }

    @Override
    public void viewCountPlus(Lost lost) {
        //将要展示的lost对象的浏览次数加1
        lost.setViewCount(lost.getViewCount() + 1);
        //更新数据库中该条数据的浏览次数
        updateLost(lost);
    }
}
