package com.woniuxy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
//角色和权限
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_and_permission")
@ApiModel(value="RoleAndPermission对象", description="")
public class RoleAndPermission implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "角色权限表id")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

        @ApiModelProperty(value = "角色id")
        private String roleId;

        @ApiModelProperty(value = "权限id")
        private String permissionId;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;


}
