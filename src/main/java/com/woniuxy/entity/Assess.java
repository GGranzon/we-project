package com.woniuxy.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("t_assess")
@ApiModel(value="Assess对象", description="")
public class Assess implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "估价序号")
        @TableId(value = "assess_id", type = IdType.ID_WORKER)
    private Integer assessId;

        @ApiModelProperty(value = "商品序号")
        private Integer productId;

        @ApiModelProperty(value = "流程实例id")
        private String instanceId;

        @ApiModelProperty(value = "官方价")
        private BigDecimal officialPrice;

        @ApiModelProperty(value = "评估价")
        private BigDecimal assessPrice;

        @ApiModelProperty(value = "典当价")
        private BigDecimal pawnPrice;

        @ApiModelProperty(value = "收购价")
        private BigDecimal buyPrice;

        @ApiModelProperty(value = "售卖价")
        private BigDecimal salePrice;

        @ApiModelProperty(value = "租价")
        private BigDecimal rentPrice;

        @ApiModelProperty(value = "备注")
        private String note;

        @ApiModelProperty(value = "创建人")
        private String createBy;

        @ApiModelProperty(value = "创建时间")
        private Date createTime;

        @ApiModelProperty(value = "修改人")
        private String modifyBy;

        @ApiModelProperty(value = "修改时间")
        private Date modifyTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;


}
