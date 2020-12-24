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
//组和类别
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_group_and_category")
@ApiModel(value="GroupAndCategory对象", description="")
public class GroupAndCategory implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "属性组_分类表id")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

        @ApiModelProperty(value = "属性组编号")
        private String groupId;

        @ApiModelProperty(value = "分类编号")
        private String categoryId;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;


}
