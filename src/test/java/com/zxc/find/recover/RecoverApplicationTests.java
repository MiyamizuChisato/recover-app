package com.zxc.find.recover;

import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.mapper.ArticleMapper;
import com.zxc.find.recover.mapper.FindMapper;
import com.zxc.find.recover.mapper.TypeMapper;
import com.zxc.find.recover.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@SpringBootTest
class RecoverApplicationTests {
    @Autowired
    private FindMapper mapper;

    @Test
    void contextLoads() {
        Find find = mapper.selectFindById(1);
        System.out.println(find);
    }

}
