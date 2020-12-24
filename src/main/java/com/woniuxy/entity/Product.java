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
@TableName("t_product")
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "商品序号")
        @TableId(value = "product_id", type = IdType.ID_WORKER)
    private String productId;

        @ApiModelProperty(value = "原商品序号")
        private String sourceProductId;

        @ApiModelProperty(value = "流程实例ID")
        private String instanceId;

        @ApiModelProperty(value = "品牌编号")
        private String brandId;

        @ApiModelProperty(value = "分类编号")
        private String categoryId;

        @ApiModelProperty(value = "二级分类")
        private String secondCatId;

        @ApiModelProperty(value = "三级分类")
        private String thirdCatId;

        @ApiModelProperty(value = "商品名称")
        private String productName;

        @ApiModelProperty(value = "来源门店")
        private String shopId;

        @ApiModelProperty(value = "仓库编号")
        private String warehouseId;

        @ApiModelProperty(value = "商品货号")
        private String articleNumber;

        @ApiModelProperty(value = "客户首买价")
        private BigDecimal firstPrice;

        @ApiModelProperty(value = "官方价")
        private BigDecimal officialPrice;

        @ApiModelProperty(value = "评估价")
        private BigDecimal valuationPrice;

        @ApiModelProperty(value = "典当价")
        private BigDecimal pawnPrice;

        @ApiModelProperty(value = "收购价")
        private BigDecimal purchasePrice;

        @ApiModelProperty(value = "售卖价")
        private BigDecimal salePrice;

        @ApiModelProperty(value = "租价")
        private BigDecimal rentPrice;

        @ApiModelProperty(value = "商品描述")
        private String productDesc;

        @ApiModelProperty(value = "录入人")
        private byte[] inputUser;

        @ApiModelProperty(value = "录入时间")
        private Date inputDate;

        @ApiModelProperty(value = "鉴定人")
        private String surveyor;

        @ApiModelProperty(value = "鉴定时间")
        private Date surveyTime;

        @ApiModelProperty(value = "评估人")
        private String assessor;

        @ApiModelProperty(value = "评估时间")
        private Date assessTime;

        @ApiModelProperty(value = "是否可租,1表示肯定，0表示否定")
        private String isRentable;

        @ApiModelProperty(value = "是否可售，1表示肯定，0表示否定")
        private String isSalable;

        @ApiModelProperty(value = "商品来源")
        private String sourceType;

        @ApiModelProperty(value = "是否处于流程中，1表肯定，0表示否定")
        private String isInProc;

        @ApiModelProperty(value = "库存状态")
        private String inventoryStatus;

        @ApiModelProperty(value = "商品状态")
        private String productStatus;

        @ApiModelProperty(value = "创建人")
        private String createBy;

        @ApiModelProperty(value = "创建时间")
        private Date createTime;

        @ApiModelProperty(value = "修改人")
        private String modifyBy;

        @ApiModelProperty(value = "修改时间")
        private Date modifyTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private ProductCategory firstProductCategory;
    @TableField(exist = false)
    private ProductCategory secondProductCategory;
    @TableField(exist = false)
    private User inUser;
    @TableField(exist = false)
    private User suUser;
    @TableField(exist = false)
    private User asUser;



}
