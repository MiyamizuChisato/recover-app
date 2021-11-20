package com.zxc.find.recover;

import com.zxc.find.recover.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RecoverApplicationTests {

    @Test
    void contextLoads() {
        Response succeed = Response.SUCCEED()
                .carry("name", "张三");
        System.out.println(succeed.getTags());
    }

}
