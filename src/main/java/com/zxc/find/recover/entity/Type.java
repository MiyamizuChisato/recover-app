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
@TableName("recover_type")
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    // 类别唯一标识，后端自动生成

    @TableId(type = IdType.AUTO, value = "type_id")
    private Integer id;

    // 类别名称

    @TableField("type_name")
    private String name;
}
