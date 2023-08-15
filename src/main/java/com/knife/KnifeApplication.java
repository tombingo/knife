package com.knife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 86151
 */
@SpringBootApplication
@MapperScan("com.knife.mybatis.mapper")
public class KnifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnifeApplication.class, args);
    }

}
