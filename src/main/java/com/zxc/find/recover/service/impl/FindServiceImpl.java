package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.FindMapper;
import com.zxc.find.recover.mapper.MessageMapper;
import com.zxc.find.recover.service.FindService;
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
 * @author Miyam
 */
@Service
@Transactional
public class FindServiceImpl implements FindService {
    @Autowired
    private FindMapper findMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Find> getFindIndex() {
        return findMapper.selectFindsForIndex();
    }

    @Override
    public Find getFindById(Integer id) {
        Find find = findMapper.selectFindById(id);
        if (find != null){
            viewCountPlus(find);
        }
        return find;
    }

    @Override
    public int addNewFind(Find find, MultipartFile picture) {
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
                find.getArticle().setPicture("/images/article/" + uuid + suffix);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            find.getArticle().setPicture("/images/article/" + "article.png");
        }
        int articleResult = articleMapper.insert(find.getArticle());
        java.util.Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        find.setStartTime(t);
        find.setArticleId(find.getArticle().getId());
        find.setArticle(null);
        int findResult = findMapper.insert(find);
        if (findResult > 0 && articleResult > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public int updateFind(Find find) {
        QueryWrapper<Find> wrapper = new QueryWrapper<>();
        wrapper.eq("find_id", find.getId());
        return findMapper.update(find, wrapper);
    }

    @Override
    public int deleteFind(Find find) {
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
        updateFind(find);
    }
}