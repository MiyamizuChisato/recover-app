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
@TableName("recover_users")
public class User {
    @TableId(type = IdType.AUTO,value = "user_id")
    private Integer id;
    @TableField("user_email")
    private String email;
    @TableField("user_phone")
    private String phone;
    @TableField("user_wechat")
    private String wechat;
    @TableField("user_qq")
    private String qq;
    @TableField("user_password")
    private String password;
    @TableField("user_name")
    private String name;
    @TableField("user_avatar")
    private String avatar;
    @TableField("user_gender")
    private Integer gender;
}
