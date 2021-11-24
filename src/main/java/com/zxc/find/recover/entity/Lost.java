package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_lost")
public class Lost implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO, value = "lost_id")
    private Integer id;
    @TableField("lost_claim")
    private Integer claim;
    @TableField("lost_view_count")
    private Integer viewCount;
    @TableField("lost_start_time")
    private Timestamp startTime;
    @TableField("lost_end_time")
    private Timestamp endTime;
    @TableField("lost_detail")
    private String detail;
    @TableField(exist = false)
    private User startUser;
    @TableField("fk_start_user_id")
    private Integer startUserId;
    @TableField(exist = false)
    private User endUser;
    @TableField("fk_end_user_id")
    private Integer endUserId;
    @TableField(exist = false)
    private Article article;
    @TableField("fk_article_id")
    private Integer articleId;
    @TableField(exist = false)
    private List<Message> messages;
}
