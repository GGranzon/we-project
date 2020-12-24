package com.woniuxy.service;

import com.woniuxy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */

public interface UserService extends IService<User> {

    //注册
    int register(User user);
    //修改
    int update(User user);
}
