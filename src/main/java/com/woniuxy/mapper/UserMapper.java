package com.woniuxy.mapper;

import com.woniuxy.entity.Permission;
import com.woniuxy.entity.Role;
import com.woniuxy.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    //查询当前登录用户所拥有的角色
    @Select("SELECT DISTINCT\n" +
            "\tr.id,r.rolename\n" +
            "FROM\n" +
            "\tt_user u\n" +
            "LEFT JOIN\n" +
            "\tt_user_and_role ur\n" +
            "ON\n" +
            "\tu.id = ur.user_id\n" +
            "LEFT JOIN\n" +
            "\tt_role r\n" +
            "ON\n" +
            "\tr.id = ur.role_id\n" +
            "LEFT JOIN\n" +
            "\tt_role_and_permission rp\n" +
            "ON\n" +
            "\tr.id = rp.role_id\n" +
            "LEFT JOIN\n" +
            "\tt_permission p\n" +
            "ON\n" +
            "\trp.permission_id = p.id\n" +
            "WHERE\n" +
            "\tu.username = #{username}")
    List<Role> getRoles(String username);

    //根据用户id查询其拥有的权限名（登录后能看到的菜单）
    @Select("SELECT DISTINCT\n" +
            "\tp.id,p.`level`,p.parent_id,p.permission_name\n" +
            "FROM\n" +
            "\tt_user u\n" +
            "LEFT JOIN\n" +
            "\tt_user_and_role ur\n" +
            "ON\n" +
            "\tu.id = ur.user_id\n" +
            "LEFT JOIN\n" +
            "\tt_role r\n" +
            "ON\n" +
            "\tr.id = ur.role_id\n" +
            "LEFT JOIN\n" +
            "\tt_role_and_permission rp\n" +
            "ON\n" +
            "\tr.id = rp.role_id\n" +
            "LEFT JOIN\n" +
            "\tt_permission p\n" +
            "ON\n" +
            "\trp.permission_id = p.id\n" +
            "WHERE\n" +
            "\tu.id = #{id}")
    List<Permission> queryPermissionsByUserId(String userId);



}
