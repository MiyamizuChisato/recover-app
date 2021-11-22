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
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@SpringBootTest
class RecoverApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/avatar";
        System.out.println(path);
    }

}
