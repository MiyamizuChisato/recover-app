package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Miyam
 */
public interface UserService {
    public User login(User user);

    public int register(User user, MultipartFile avatar);
}
