package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.User;
import com.woniuxy.jwt.JwtUtil;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.service.UserService;
import com.woniuxy.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

//    /**
//     * 注册，只有最高权限拥有者才能注册用户
//     * @param user
//     * @return
//     */
//    @RequestMapping("/index/register")
//    public Result register(User user){
//        System.out.println("进入了注册");
//        User selectUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//        if(ObjectUtils.isEmpty(selectUser)){
//            if(user.getPassword() != null){
//                String salt = SaltUtils.getSalt(8);
//                Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
//                user.setPassword(md5Hash.toHex());
//                user.setSalt(salt);
//                String s = UUID.randomUUID().toString().replaceAll("-", "");
//                System.out.println(s);
//                user.setId(s);
//
//                int row = userService.register(user);
//                if(row > 0){
//                    return new Result(true, StatusCode.OK,"注册成功");
//                }else {
//                    return new Result(false,StatusCode.FAILED,"注册失败");
//                }
//            }else {
//                return new Result(false,StatusCode.FAILED,"输入密码为空，注册失败");
//            }
//        }
//
//        return new Result(false,StatusCode.FAILED,"用户名已存在，注册失败");
//    }

    @RequestMapping("/login")
    public Result login(String username, String password, HttpServletRequest request){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(!ObjectUtils.isEmpty(user)){
            String salt = user.getSalt();
            String hashPassword = new Md5Hash(password,salt,1024).toHex();
            //判断用户输入的密码经过加密后是否和数据库存的密码相同
            if(hashPassword.equals(user.getPassword())){
                String token = JwtUtil.createToken(username);
                //登录成功返回 token 给到前端
                return new Result(true,StatusCode.OK,"登录成功！",token);
            }
            return new Result(false,StatusCode.FAILED,"账号密码不匹配");
        }
        return new Result(false,StatusCode.FAILED,"当前用户尚未注册");
    }

    @RequestMapping("/update")
    public Result update(){
        return new Result(true,StatusCode.OK,"修改成功");
    }

}

