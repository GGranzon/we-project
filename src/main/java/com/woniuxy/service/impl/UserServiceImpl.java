package com.woniuxy.service.impl;

import com.woniuxy.entity.User;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public int register(User user) {
        System.out.println("进入了注册方法");
        int insert = userMapper.insert(user);
        System.out.println("====="+insert);
        return insert;
    }

    @Override
    public int update(User user) {
        int i = userMapper.updateById(user);
        return i;
    }
}
