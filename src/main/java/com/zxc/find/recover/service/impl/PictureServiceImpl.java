package com.zxc.find.recover.service.impl;

import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.UserMapper;
import com.zxc.find.recover.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/5 20:10
 */
@Component
public class PictureServiceImpl implements PictureService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int articlePic(Integer id, MultipartFile articlePic) {
        try {
            if (StringUtils.hasLength(articlePic.getOriginalFilename())) {
                String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/article";
                String fileName = randomFileName(articlePic);
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String articlePicPath = path + "/" + fileName;
                articlePic.transferTo(new File(articlePicPath));
                Article article = articleMapper.selectArticleById(id);
                article.setPicture(articlePicPath);
                int success = articleMapper.updateById(article);
                if (success > 0){
                    return 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int headPic(Integer id, MultipartFile headPic) {
        try {
            if (StringUtils.hasLength(headPic.getOriginalFilename())) {
                String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/avatar";
                String fileName = randomFileName(headPic);
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String headPicPath = path + "/" + fileName;
                headPic.transferTo(new File(headPicPath));
                User user = userMapper.selectUserById(id);
                user.setAvatar(headPicPath);
                int success = userMapper.updateById(user);
                if (success > 0){
                    return 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String randomFileName(MultipartFile picture){
        String uuid = UUID.randomUUID().toString();
        String suffix = picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf("."));
        return uuid + suffix;
    }
}
