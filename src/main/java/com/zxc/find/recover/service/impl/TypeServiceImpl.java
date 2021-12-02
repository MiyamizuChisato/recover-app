package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.TypeMapper;
import com.zxc.find.recover.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Miyam
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectList(null);
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public int updateType(Type type) {
        QueryWrapper<Type> typeWrapper = new QueryWrapper<>();
        typeWrapper.eq("type_id",type.getId());
        return typeMapper.update(type,typeWrapper);
    }

    @Override
    public int deleteType(Integer id) {
        QueryWrapper<Type> typeWrapper = new QueryWrapper<>();
        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
        articleWrapper.eq("fk_type_id",id);
        List<Article> articles = articleMapper.selectList(articleWrapper);
        if (articles == null){//说明物品表中已经没有该类型的物品
            typeWrapper.eq("type_id",id);
            typeMapper.delete(typeWrapper);
            return 1;
        }
        return -1;
    }
}
