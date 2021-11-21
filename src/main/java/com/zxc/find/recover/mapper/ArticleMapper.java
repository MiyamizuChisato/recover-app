package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * @author Miyam
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    public Article selectArticleById(Integer id);
}
