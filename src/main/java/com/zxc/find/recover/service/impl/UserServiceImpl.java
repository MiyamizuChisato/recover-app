package com.zxc.find.recover.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.UserMapper;
import com.zxc.find.recover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

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
        try {
            if (StringUtils.hasLength(avatar.getOriginalFilename())) {
                String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/avatar";
                String uuid = UUID.randomUUID().toString();
                String suffix = avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf("."));
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String avatarPath = path + "/" + uuid + suffix;
                avatar.transferTo(new File(avatarPath));
                user.setAvatar("/images/avatar/" + uuid + suffix);
            } else {
                user.setAvatar("/images/avatar/avatar.webp");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapper.insert(user);
    }
}
