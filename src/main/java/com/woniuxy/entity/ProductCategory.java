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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product_category")
@ApiModel(value="ProductCategory对象", description="")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "商品分类id")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

        @ApiModelProperty(value = "分类名称")
        private String categoryName;

        @ApiModelProperty(value = "上级分类")
        private String categoryParent;

        @ApiModelProperty(value = "分类级次")
        private Integer categoryLevel;

        @ApiModelProperty(value = "分类路径")
        private String categoryPath;

        @ApiModelProperty(value = "数量单位")
        private String numUnit;

        @ApiModelProperty(value = "分类描述")
        private String description;

        @ApiModelProperty(value = "鉴定图定义")
        private String defin;

        @ApiModelProperty(value = "是否显示")
        private String isShow;

        @ApiModelProperty(value = "排序")
        private String sort;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;


}
