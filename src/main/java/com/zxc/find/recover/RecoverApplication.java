package com.zxc.find.recover;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Miya
 */
@SpringBootApplication
@MapperScan("com.zxc.find.recover.mapper")
public class RecoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecoverApplication.class, args);
    }

}
