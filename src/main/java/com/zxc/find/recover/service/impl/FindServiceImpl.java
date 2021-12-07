package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.FindMapper;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Miyam
 */
@Component
@Transactional
public class FindServiceImpl implements CRUDService<Find> {
    @Autowired
    private FindMapper findMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Find> getIndex() {
        return findMapper.selectFindsForIndex();
    }

    @Override
    public Find getInfoById(Integer id) {
        Find find = findMapper.selectFindById(id);
        if (find != null){
            viewCountPlus(find);
        }
        return find;
    }

    @Override
    public int addNewInfo(Find find) {
        int articleResult = articleMapper.insert(find.getArticle());
        java.util.Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        find.setStartTime(t);
        find.setArticleId(find.getArticle().getId());
        find.setArticle(null);
        int findResult = findMapper.insert(find);
        if (findResult > 0 && articleResult > 0) {
            return find.getArticle().getId();
        }
        return -1;
    }

    @Override
    public int updateInfo(Find find) {
        QueryWrapper<Find> wrapper = new QueryWrapper<>();
        wrapper.eq("find_id", find.getId());
        return findMapper.update(find, wrapper);
    }

    @Override
    public int deleteInfo(Find find) {
        QueryWrapper<Find> wrapperFind = new QueryWrapper<>();
        QueryWrapper<Message> wrapperMessage = new QueryWrapper<>();
        QueryWrapper<Article> wrapperArticle = new QueryWrapper<>();
        wrapperFind.eq("find_id", find.getId());
        wrapperArticle.eq("article_id", find.getArticle().getId());
        wrapperMessage.eq("fk_find_id", find.getId());
        int deleteFind = findMapper.delete(wrapperFind);
        int deleteArticle = articleMapper.delete(wrapperArticle);
        int deleteMessage = messageMapper.delete(wrapperMessage);
        if (deleteFind > 0 && deleteArticle > 0 && deleteMessage > 0){
            return 1;
        }
        return -1;
    }

    @Override
    public void viewCountPlus(Find find) {
        find.setViewCount(find.getViewCount() + 1);
        updateInfo(find);
    }
}