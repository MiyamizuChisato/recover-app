package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.FindMapper;
import com.zxc.find.recover.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
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
    private FindMapper mapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Find> getFindIndex() {
        return mapper.selectFindsForIndex();
    }

    @Override
    public Find getFindById(Integer id) {
        return mapper.selectFindById(id);
    }

    @Override
    public int addNewFind(Find find, MultipartFile picture) {
        if (StringUtils.hasLength(picture.getOriginalFilename())) {
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
        int findResult = mapper.insert(find);
        if (findResult > 0 && articleResult > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public int updateFind(Find find) {
        QueryWrapper<Find> wrapper = new QueryWrapper<>();
        wrapper.eq("find_id", find.getId());
        find.setStartUser(null);
        find.setEndUser(null);
        find.setArticle(null);
        find.setMessages(null);
        return mapper.update(find, wrapper);
    }

    @Override
    public void viewCountPlus(Find find) {
        find.setViewCount(find.getViewCount() + 1);
        updateFind(find);
    }
}
