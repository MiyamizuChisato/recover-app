package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO, value = "article_id")
    private Integer id;
    @TableField("article_picture")
    private String picture;
    @TableField("article_name")
    private String name;
    @TableField("article_summary")
    private String summary;
    @TableField(exist = false)
    private Type type;
    @TableField("fk_type_id")
    private Integer typeId;
}
