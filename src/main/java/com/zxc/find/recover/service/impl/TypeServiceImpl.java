package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.TypeMapper;
import com.zxc.find.recover.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Miyam
 */
@Component
@Transactional
public class TypeServiceImpl implements CRUDService<Type> {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Type> getIndex() {
        return typeMapper.selectList(null);
    }

    @Override
    public int addNewInfo(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public int updateInfo(Type type) {
        QueryWrapper<Type> typeWrapper = new QueryWrapper<>();
        typeWrapper.eq("type_id",type.getId());
        return typeMapper.update(type,typeWrapper);
    }

    @Override
    public int deleteInfo(Type type) {
        QueryWrapper<Type> typeWrapper = new QueryWrapper<>();
        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
        articleWrapper.eq("fk_type_id",type.getId());
        List<Article> articles = articleMapper.selectList(articleWrapper);
        if (articles == null){//说明物品表中已经没有该类型的物品
            typeWrapper.eq("type_id",type.getId());
            typeMapper.delete(typeWrapper);
            return 1;
        }
        return -1;
    }

    //必须要实现，不能使用抽象类，否则无法运行
    @Override
    public Type getInfoById(Integer id) {
        return null;
    }

    @Override
    public void viewCountPlus(Type entity) {
    }
}
