package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Miyam
 */
@Service
public interface UserService {
    public User login(User user);

    public int register(User user);

    public int updateUser(User user);

    public User getUserById(Integer id);
}