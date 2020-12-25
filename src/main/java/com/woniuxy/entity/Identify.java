package com.woniuxy.entity;

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
//鉴定表
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_identify")
@ApiModel(value="Identify对象", description="")
public class Identify implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "鉴定序号")
        @TableId(value = "identify_id", type = IdType.ID_WORKER)
    private String identifyId;

        @ApiModelProperty(value = "商品序号")
        private String productId;

        @ApiModelProperty(value = "流程实例id")
        private String instanceId;

        @ApiModelProperty(value = "新旧程度")
        private String degree;

        @ApiModelProperty(value = "备注")
        private String note;

        @ApiModelProperty(value = "鉴定结果")
        private String result;

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
