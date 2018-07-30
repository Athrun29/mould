package org.zuel.mould;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ServletComponentScan
@EnableWebMvc
@MapperScan("org.zuel.mould.dao")
@SpringBootApplication
public class MouldApplication {

    public static void main(String[] args) {
        SpringApplication.run(MouldApplication.class, args);
    }
}
