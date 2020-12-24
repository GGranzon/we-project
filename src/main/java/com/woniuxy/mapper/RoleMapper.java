package com.woniuxy.mapper;

import com.woniuxy.entity.Permission;
import com.woniuxy.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("SELECT\n" +
            "\tp.permission_name,p.id,p.`level`,parent_id\n" +
            "FROM\n" +
            "\tt_role r\n" +
            "LEFT JOIN\n" +
            "\tt_role_and_permission rp\n" +
            "ON\n" +
            "\tr.id = rp.role_id\n" +
            "LEFT JOIN\n" +
            "\tt_permission p\n" +
            "ON\n" +
            "\trp.permission_id = p.id\n" +
            "WHERE\n" +
            "\tr.id = #{id}")
    List<Permission> getPermissions(String roleId);
}
