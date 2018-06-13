package com.guo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 配置spring自动扫描 mapper接口
 */
@EnableCaching // 开启缓存功能
@MapperScan("com.guo.springboot.dao") // mybatis 扫描dao接口
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
