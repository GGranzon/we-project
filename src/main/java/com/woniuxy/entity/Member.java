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
@TableName("t_member")
@ApiModel(value="Member对象", description="")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "会员编号")
        @TableId(value = "member_id", type = IdType.ID_WORKER)
    private String memberId;

        @ApiModelProperty(value = "会员姓名")
        private String name;

        @ApiModelProperty(value = "手机")
        private String tel;

        @ApiModelProperty(value = "证件号码")
        private String cardNum;

        @ApiModelProperty(value = "账户名称")
        private String accountName;

    private String openingBank;

        @ApiModelProperty(value = "账号")
        private String accountNum;

        @ApiModelProperty(value = "生日")
        private Date birthday;

        @ApiModelProperty(value = "会员等级")
        private String level;

        @ApiModelProperty(value = "可用余额")
        private BigDecimal balance;

        @ApiModelProperty(value = "冻结金额")
        private BigDecimal frozenMoney;

        @ApiModelProperty(value = "透支金额")
        private BigDecimal overdraft;

        @ApiModelProperty(value = "是否允许透支，1代表允许，0代表不允许")
        private Integer overdraftAble;

        @ApiModelProperty(value = "可用积分")
        private Integer ableCount;

        @ApiModelProperty(value = "累计积分")
        private Integer allCount;

        @ApiModelProperty(value = "入会日期")
        private Date joinDate;

        @ApiModelProperty(value = "所属渠道")
        private String distributorId;

        @ApiModelProperty(value = "状态")
        private String status;

        @ApiModelProperty(value = "创建人")
        private String createBy;

        @ApiModelProperty(value = "创建时间")
        private Date createTime;

        @ApiModelProperty(value = "修改人")
        private String modifyBy;

        @ApiModelProperty(value = "修改人")
        private Date modifyTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;


}
