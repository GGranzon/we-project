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
@TableName("t_distributor")
@ApiModel(value="Distributor对象", description="")
public class Distributor implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "渠道商id")
        @TableId(value = "id", type = IdType.ID_WORKER)
        private String id;

        @ApiModelProperty(value = "渠道名")
        private String name;

        @ApiModelProperty(value = "手机号")
        private String tel;

        @ApiModelProperty(value = "证件号码")
        private String cardCode;

        @ApiModelProperty(value = "账户名称")
        private String accountName;

        @ApiModelProperty(value = "开户行")
        private String openingBank;

        @ApiModelProperty(value = "账号")
        private String  accountCode;

        @ApiModelProperty(value = "会员数量")
        private Integer memberNum;

        @ApiModelProperty(value = "累计充值")
        private BigDecimal allCharge;

        @ApiModelProperty(value = "累计消费")
        private BigDecimal allPay;

        @ApiModelProperty(value = "状态")
        private String status;

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


}
