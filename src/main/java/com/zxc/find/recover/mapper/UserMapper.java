package com.zxc.find.recover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.find.recover.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Miyam
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过id查询用户全部信息
     *
     * @return User
     */
    public User selectUserById(Integer id);
}
