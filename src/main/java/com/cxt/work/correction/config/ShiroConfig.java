package com.cxt.work.correction.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *     shiro配置类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/28 13:44
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro工厂过滤器
     * @param securityManager 安全管理器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier(value = "securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 添加shiro内置的过滤器：
         * anon: 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user: 必须拥有 记住我 的功能才能访问
         * perms: 拥有对某个资源的权限才能访问
         * role: 拥有某个角色权限才能访问
         */

        Map<String, String> finitionMap = new LinkedHashMap<>();
        finitionMap.put("/toAdd", "perms[user:add]");
        finitionMap.put("/toMain", "anon");

        // 设置登录url
//        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 设置未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUnauthoriz");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(finitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     * DefaultWebSecurityManager
     * @param userRealm realm对象
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建realm对象
     * @Bean(name = "userRealm") 可以这里指定一个名称
     *
     * @return realm对象
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
