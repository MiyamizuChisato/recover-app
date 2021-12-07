package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.LostMapper;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:40
 */
@Component
@Transactional
public class LostServiceImpl implements CRUDService<Lost> {
    @Autowired
    private LostMapper lostMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Lost> getIndex() {
        return lostMapper.selectLostForIndex();
    }

    @Override
    public Lost getInfoById(Integer id) {
        Lost lost = lostMapper.selectLostById(id);
        if (lost != null){
            viewCountPlus(lost);
        }
        return lost;
    }

    @Override
    public int addNewInfo(Lost lost) {
        int articleResult = articleMapper.insert(lost.getArticle());
        java.util.Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        lost.setStartTime(t);
        lost.setArticleId(lost.getArticle().getId());
        lost.setArticle(null);
        int lostResult = lostMapper.insert(lost);
        if (lostResult > 0 && articleResult > 0) {
            return lost.getArticle().getId();
        }
        return -1;
    }

    @Override
    public int updateInfo(Lost lost) {
        QueryWrapper<Lost> wrapper = new QueryWrapper<>();
        wrapper.eq("lost_id", lost.getId());
        return lostMapper.update(lost, wrapper);
    }

    @Override
    public int deleteInfo(Lost lost) {
        QueryWrapper<Lost> wrapperLost = new QueryWrapper<>();
        QueryWrapper<Message> wrapperMessage = new QueryWrapper<>();
        QueryWrapper<Article> wrapperArticle = new QueryWrapper<>();
        wrapperLost.eq("lost_id", lost.getId());
        wrapperArticle.eq("article_id", lost.getArticle().getId());
        wrapperMessage.eq("fk_lost_id", lost.getId());
        int deleteLost = lostMapper.delete(wrapperLost);
        int deleteArticle = articleMapper.delete(wrapperArticle);
        int deleteMessage = messageMapper.delete(wrapperMessage);
        if (deleteLost > 0 && deleteArticle > 0 && deleteMessage > 0){
            return 1;
        }
        return -1;
    }

    @Override
    public void viewCountPlus(Lost lost) {
        lost.setViewCount(lost.getViewCount() + 1);
        updateInfo(lost);
    }
}