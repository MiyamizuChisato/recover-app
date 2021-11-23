package com.zxc.find.recover.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Miyam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO, value = "user_id")
    private Integer id;
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^[A-Za-z0-9-_]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱输入不合法")
    @TableField("user_email")
    private String email;
    @TableField("user_phone")
    private String phone;
    @TableField("user_wechat")
    private String wechat;
    @TableField("user_qq")
    private String qq;
    @NotBlank(message = "密码不能为空")
    @TableField("user_password")
    private String password;
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", message = "姓名输入不合法")
    @TableField("user_name")
    private String name;
    @TableField("user_avatar")
    private String avatar;
    @TableField("user_gender")
    private Integer gender;
}
