package com.cxt.work.correction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *     启动类
 * </p>
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cxt.work.correction.mapper")
@EnableSwagger2
public class WorkCorrectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkCorrectionApplication.class, args);
    }
}
