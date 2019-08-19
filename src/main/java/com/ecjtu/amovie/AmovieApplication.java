package com.ecjtu.amovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan("com.ecjtu.amovie.repository")
public class AmovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmovieApplication.class, args);
    }

}
