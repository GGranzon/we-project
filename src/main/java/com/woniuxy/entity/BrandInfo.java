package com.woniuxy.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_brandinfo")
@ApiModel(value="Brandinfo对象", description="")
public class Brandinfo implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "品牌编号")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "品牌首字母")
        private String brandInitial;

        @ApiModelProperty(value = "品牌简介")
        private String brandInfo;

        @ApiModelProperty(value = "是否展示")
        private String isShow;

        @ApiModelProperty(value = "排序")
        private String sort;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
