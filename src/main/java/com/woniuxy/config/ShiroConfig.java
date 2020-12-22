package com.woniuxy.config;

import com.woniuxy.component.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    //1、注册ShiroFilter
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();

//        //设置白名单 放行axios请求
//        linkedHashMap.put("/user/login","anon");
//        //设置白名单 放行静态资源
//        linkedHashMap.put("/js/**","anon");
//
//        linkedHashMap.put("/page/register.html","anon");
//        //user 表示设置黑名单的 UserFilter,拦截所有
//        linkedHashMap.put("/**","user");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
//        shiroFilterFactoryBean.setLoginUrl("/page/login.html");

        return shiroFilterFactoryBean;
    }
    //2、注册securityManager
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customerRealm());
        return securityManager;
    }
    //3、注册realm
    @Bean
    public CustomerRealm customerRealm(){
        CustomerRealm realm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);

        realm.setCredentialsMatcher(hashedCredentialsMatcher);

//        //开启缓存管理器
//        realm.setCachingEnabled(true);
//        realm.setAuthenticationCachingEnabled(true);
//        realm.setAuthorizationCachingEnabled(true);
//        //引入EhCache缓存
//        realm.setCacheManager(new EhCacheManager());

        return realm;
    }


    /**
     * 添加注解支持
     * DefaultAdvisorAutoProxyCreator是用来扫描上下文，
     * 寻找所有的Advistor(通知器），
     * 将这些Advisor应用到所有符合切入点的Bean中。
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置通知器，指定扫描对应注解的类
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    /**
     * LifecycleBeanPostProcessor：
     * 将Initializable和Destroyable的实现类统一在其内部自动分别调用了Initializable.init()和Destroyable.destroy()方法，从而达到管理shiro bean生命周期的目的
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
