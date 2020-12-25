package com.woniuxy.config;

import com.woniuxy.component.CustomerRealm;
import com.woniuxy.jwt.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 注册 RememberMe 所需组件
     * @return 返回rememberMe管理器
     */
//    @Bean
//    public CookieRememberMeManager cookieRememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        SimpleCookie rememberMe = new SimpleCookie("rememberMe");
//        //设置最大生效时间 一周
//        rememberMe.setMaxAge(7*24*60*60);
//        cookieRememberMeManager.setCookie(rememberMe);
//        cookieRememberMeManager.setCipherKey(Base64.decode("a1b2c3d4e5f6h7j8k9l10m=="));
//        return cookieRememberMeManager;
//    }


    //1、注册ShiroFilter

    /**
     * 先走 filter ，然后 filter 如果检测到请求头存在 token ，则用 token 去 login,走 Realm去验证
     * @param securityManager
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //添加自己的jwt过滤器进 shiro 的过滤器列表
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //设置自定义的 JWT 过滤器
        filterMap.put("jwt",new JwtFilter());
        filterFactoryBean.setFilters(filterMap);
        filterFactoryBean.setSecurityManager(securityManager);

        //设置过滤请求
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        //只有登录界面允许匿名访问
        linkedHashMap.put("/login","anon");
        //所有请求由 自定义的jwt Filter过滤
        linkedHashMap.put("/**","jwt");

        filterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        return filterFactoryBean;
    }
    //2、注册securityManager
    @Bean
    public DefaultWebSecurityManager securityManager(CustomerRealm customerRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置自定义 realm
        securityManager.setRealm(customerRealm);

        /**
         * 关闭shiro自带的 session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }
//    //3、注册realm
//    @Bean
//    public CustomerRealm customerRealm(){
//        CustomerRealm realm = new CustomerRealm();
//
//        return realm;
//    }


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
