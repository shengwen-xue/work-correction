package com.cxt.work.correction.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 *     swagger配置类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/22 12:52
 */
@Configuration
@ConditionalOnProperty(prefix = "msg-config", name = "swagger-open", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 这里采用包扫描的方式来确定要显示的接口
                //.apis(RequestHandlerSelectors.basePackage("com.moerlong.service_authorize.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("Work Correction Doc")
                // 描述
                .description("作业批改系统 Api文档")
                // 条款地址（不可见）
                .termsOfServiceUrl("http://www.baidu.com")
                // 作者信息
                .contact("xue-sheng-wen")
                // 版本号
                .version("1.0")
                .build();
    }
}
