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
import java.util.List;

/**
 * @author Miyam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("recover_finds")
public class Find implements Serializable {

    // 招领表唯一标识，后端自动生成

    @TableId(type = IdType.AUTO, value = "find_id")
    private Integer id;

    // 招领是否完成，默认为0——未完成，创建时，前端不用传递该参数

    @TableField("find_claim")
    private Integer claim;

    // 招领页面浏览次数，默认为0，创建时，前端不用传递该参数

    @TableField("find_view_count")
    private Integer viewCount;

    // 招领创建时间，默认为当前时间，创建时，前端不用传递该参数

    @TableField("find_start_time")
    private Date startTime;

    // 招领完成时间，默认为空，创建时，前端不用传递该参数

    @TableField("find_end_time")
    private Date endTime;

    // 招领内容细节

    @TableField("find_detail")
    private String detail;

    // 招领发布用户ID，创建时前端发送Token，不用传递改参数

    private User startUser;

    // 失主用户ID，默认为空，前端创建时不用传递改参数

    private User endUser;

    // 招领具体物品，详细见article类

    private Article article;

    private List<Message> messages;
}
