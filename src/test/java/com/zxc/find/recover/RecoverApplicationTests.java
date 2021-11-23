package com.zxc.find.recover;

import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RecoverApplicationTests {
    @Autowired
    private UserMapper mapper;

    @Test
    void contextLoads() {
        User user = mapper.selectUserById(1);
        System.out.println(user);
    }

}
