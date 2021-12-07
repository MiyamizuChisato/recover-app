package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.UserMapper;
import com.zxc.find.recover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Miyam
 */

@Component
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_email", user.getEmail());
        wrapper.eq("user_password", user.getPassword());
        return mapper.selectOne(wrapper);
    }

    @Override
    public int register(User user) {
        return mapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateById(user);
    }

    @Override
    public User getUserById(Integer id) {
        return mapper.selectUserById(id);
    }
}
