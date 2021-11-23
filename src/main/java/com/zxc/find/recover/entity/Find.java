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
@TableName("recover_finds")
public class Find implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO, value = "find_id")
    private Integer id;
    @TableField("find_claim")
    private Integer claim;
    @TableField("find_view_count")
    private Integer viewCount;
    @TableField("find_start_time")
    private Timestamp startTime;
    @TableField("find_end_time")
    private Timestamp endTime;
    @TableField("find_detail")
    private String detail;
    private User startUser;
    @TableField("fk_start_user_id")
    private Integer startUserId;
    private User endUser;
    @TableField("fk_end_user_id")
    private Integer endUserId;
    private Article article;
    @TableField("fk_article_id")
    private Integer articleId;
    private List<Message> messages;
}
