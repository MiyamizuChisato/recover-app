package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_lost")
public class Lost {
    @TableId(type = IdType.AUTO, value = "lost_id")
    private Integer id;
    @TableField("lost_claim")
    private Integer claim;
    @TableField("lost_view_count")
    private Integer viewCount;
    @TableField("lost_start_time")
    private Date startTime;
    @TableField("lost_end_time")
    private Date endTime;
    @TableField("lost_detail")
    private String detail;
    private User startUser;
    private User endUser;
    private Article article;
    private List<Message> messages;
}
