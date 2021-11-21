package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_articles")
public class Article {
    // 物品Id，前端不用传

    @TableId(type = IdType.AUTO, value = "article_id")
    private Integer id;

    // 物品主题图片 前端可以用file来作为ui

    @TableField("article_picture")
    private String picture;

    // 物品名称

    @TableField("article_name")
    private String name;

    // 物品描述

    @TableField("article_summary")
    private String summary;

    // 物品类别，相信信息见Type实体类

    private Type type;
}
