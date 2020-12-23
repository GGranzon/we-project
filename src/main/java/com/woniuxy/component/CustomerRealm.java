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
        //从令牌中获取用户名，查询该用户拥有的角色
        List<Role> roles = userMapper.getRoles(username);
        //创建容器装用户所拥有的 角色 和 权限
        ArrayList<String> roleNames = new ArrayList<>();
        ArrayList<String> permissionNames = new ArrayList<>();

        if(!CollectionUtils.isEmpty(roles)){
            roles.forEach(role -> {
                //打印角色名
                log.info(role.getRolename());
                //将角色名存入容器
                roleNames.add(role.getRolename());

                //根据角色查询权限
                List<Permission> permissions = roleMapper.getPermissions(role.getId());
                if(!CollectionUtils.isEmpty(permissions)){
                    //遍历权限， 将权限名存入容器
                    permissions.forEach(permission -> {
                        permissionNames.add(permission.getPermissionName());
                    });
                }
            });

            //为当前用户指定角色
            simpleAuthorizationInfo.addRoles(roleNames);
            //为当前用户指定权限
            simpleAuthorizationInfo.addStringPermissions(permissionNames);
        }

        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入了认证。。。。。。。。");

        String token = (String) authenticationToken.getCredentials();
        //解密获得username,用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        //如果未能通过解密得到username 或 token校验未通过
        if(username == null || !JwtUtil.verify(token,username)){
            throw new AuthenticationException("token认证失败！");
        }

        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
