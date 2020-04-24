package com.cxt.work.correction.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * <p>
 *     Freemarker页面引擎配置类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/3/22 11:05
 */
@Slf4j
@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

    @Autowired
    private Configuration configuration;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        log.info("开始构建shiro标签...");
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
        log.info("构建shiro标签结束...");
    }
}
