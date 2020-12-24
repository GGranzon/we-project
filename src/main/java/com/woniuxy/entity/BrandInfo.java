package com.woniuxy.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_brandinfo")
@ApiModel(value="GroupAndCategory对象", description="")
public class BrandInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性组_分类表id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "品牌首字母")
    private String brandInitial;

    @ApiModelProperty(value = "品牌描述")
    private String brandInfo;

    @ApiModelProperty(value = "是否展示")
    private String isShow;

    @ApiModelProperty(value = "排序")
    private String sort;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
