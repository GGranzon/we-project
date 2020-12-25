package com.woniuxy.component;

import com.woniuxy.entity.Permission;
import com.woniuxy.entity.Role;
import com.woniuxy.jwt.JwtToken;
import com.woniuxy.jwt.JwtUtil;
import com.woniuxy.mapper.RoleMapper;
import com.woniuxy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomerRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RedisClient redis;

    //添加对自定义的JwtToken的支持
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进入了授权。。。。。。");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username  = JwtUtil.getUsername(principals.toString());
        System.out.println(username);
        //从令牌中获取用户名，查询该用户拥有的角色 先从redis 中拿 redis中没有再从数据库中取
        List<String> roles1 = redis.lGet(username + ":roles", 0, -1);
        if(CollectionUtils.isEmpty(roles1)){
            //如果redis中没有数据，则去数据库中查 然后存入redis
            List<Role> roles = userMapper.getRoles(username);

            //创建容器装用户所拥有的 角色 和 权限
            ArrayList<String> roleNames = new ArrayList<>();
            ArrayList<String> permissionNames = new ArrayList<>();

            if(!CollectionUtils.isEmpty(roles)){
                System.out.println(1111);
                System.out.println(roles);
                roles.forEach(role -> {
                    //打印角色名
                    log.info(role.getRolename());
                    //将角色名存入容器
                    roleNames.add(role.getRolename());
                    redis.lSet(username+":roles",role.getRolename());

                    //根据角色查询权限 先从redis中拿，如果没有再从数据库中去拿
                    List<String> permissions1 = redis.lGet(username + ":permissions", 0, -1);
                    if(CollectionUtils.isEmpty(permissions1)){
                        List<Permission> permissions = roleMapper.getPermissions(role.getId());
                        if(!CollectionUtils.isEmpty(permissions)){
                            //将权限名存入Redis
                            permissions.forEach(permission -> {
                                redis.lSet(username+":permissions",permission.getPermissionName());
                            });
                            //遍历权限， 将权限名存入容器
                            permissions.forEach(permission -> {
                                permissionNames.add(permission.getPermissionName());
                            });
                        }
                        simpleAuthorizationInfo.addStringPermissions(permissionNames);
                    }else {
                        //不为空直接用redis中的权限名
                        simpleAuthorizationInfo.addStringPermissions(permissions1);
                    }
                });
                simpleAuthorizationInfo.addRoles(roleNames);
            }
        }else {
            System.out.println("开始从redis中查数据");

            //不为空，直接用从redis里查出的角色
            simpleAuthorizationInfo.addRoles(roles1);

            List<String> permissions = redis.lGet(username + ":permissions", 0, -1);
            simpleAuthorizationInfo.addStringPermissions(permissions);

        }


//        if(!CollectionUtils.isEmpty(roles)){
//            roles.forEach(role -> {
//                //打印角色名
//                log.info(role.getRolename());
//                //将角色名存入容器
//                roleNames.add(role.getRolename());
//
//                //根据角色查询权限
//                List<Permission> permissions = roleMapper.getPermissions(role.getId());
//                if(!CollectionUtils.isEmpty(permissions)){
//                    //遍历权限， 将权限名存入容器
//                    permissions.forEach(permission -> {
//                        permissionNames.add(permission.getPermissionName());
//                    });
//                }
//            });
//            //为当前用户指定角色
//            simpleAuthorizationInfo.addRoles(roleNames);
//            //为当前用户指定权限
//            simpleAuthorizationInfo.addStringPermissions(permissionNames);
//        }
//
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入了认证。。。。。。。。");

        String token = (String) authenticationToken.getCredentials();
        //解密获得username,用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        //先判断自动生成的token 是否已经过期，如果已经过期，去redis里判断是否过期
        if(!JwtUtil.verify(token,username)){

        }

        //如果未能通过解密得到username 或 token校验未通过
        if(username == null || !JwtUtil.verify(token,username)){
            throw new AuthenticationException("token认证失败！");
        }

        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
