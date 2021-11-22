package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.UserMapper;
import com.zxc.find.recover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Miyam
 */

@Service
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
    public int register(User user, MultipartFile avatar) {
        //todo 如果用户自定义了头像，那么将头像上传至项目，并记录到数据库当中。

        return mapper.insert(user);
    }
}
