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
@Service("userService")
public interface UserService extends IService<User> {

}
