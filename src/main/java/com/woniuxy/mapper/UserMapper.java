package com.woniuxy.mapper;

import com.woniuxy.entity.Role;
import com.woniuxy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface UserMapper extends BaseMapper<User> {

    //查询当前登录用户所拥有的角色
    List<Role> getRoles(String username);
}
