package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.LostMapper;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private MessageMapper messageMapper;

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
    public int addLost(Lost lost, MultipartFile picture) {
        if (picture != null) {
            try {
                String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/article";
                String uuid = UUID.randomUUID().toString();
                String suffix = picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf("."));
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String picPath = path + "/" + uuid + suffix;
                picture.transferTo(new File(picPath));
                lost.getArticle().setPicture("/images/article/" + uuid + suffix);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lost.getArticle().setPicture("/images/article/" + "article.png");
        }
        int articleResult = articleMapper.insert(lost.getArticle());
        java.util.Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        lost.setStartTime(t);
        lost.setArticleId(lost.getArticle().getId());//前端不传物品id，为null
        lost.setArticle(null);
        int lostResult = lostMapper.insert(lost);
        if (lostResult > 0 && articleResult > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public int updateLost(Lost lost) {
        QueryWrapper<Lost> wrapper = new QueryWrapper<>();
        wrapper.eq("lost_id", lost.getId());
        return lostMapper.update(lost, wrapper);
    }

    @Override
    public int deleteLost(Lost lost) {
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
        updateLost(lost);
    }
}