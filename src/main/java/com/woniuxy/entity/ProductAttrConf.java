package com.woniuxy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product_attr_conf")
@ApiModel(value="ProductAttrConf对象", description="")
public class ProductAttrConf implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "属性编号")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

        @ApiModelProperty(value = "所属组id")
        private String groupId;

        @ApiModelProperty(value = "属性名称")
        private String attrName;

        @ApiModelProperty(value = "属性类型")
        private String attrType;

        @ApiModelProperty(value = "可选值清列表")
        private String opions;

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

    @TableField(exist = false)
    private ProductAttrGroup productAttrGroup;

}
