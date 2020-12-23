package com.woniuxy.mapper;

import com.woniuxy.entity.Permission;
import com.woniuxy.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {
    //根据角色Id 获取角色权限
    List<Permission> getPermissions(String roleId);
}
