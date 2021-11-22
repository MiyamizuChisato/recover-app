package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_message")
public class Message implements Serializable {
    @TableId(type = IdType.AUTO, value = "message_id")
    private Integer id;
    @TableField("message_is_delete")
    private Integer isDelete;
    @TableField("message_date")
    private Date date;
    @TableField("message_content")
    private String content;
    private User user;
}
