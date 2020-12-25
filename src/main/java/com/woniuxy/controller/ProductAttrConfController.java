package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.ProductAttrConf;
import com.woniuxy.service.ProductAttrConfService;
import com.woniuxy.vo.PageVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/product-attr-conf")
public class ProductAttrConfController {

    @Resource
    private ProductAttrConfService productAttrConfService;

    @RequestMapping("getAttrInfo")
    public Result findAttrInfo(PageVo pageVo){
        System.out.println(pageVo);
        IPage<ProductAttrConf> attrInfo = productAttrConfService.findAttrInfo(pageVo);
        System.out.println(attrInfo);
        return new Result(true, StatusCode.OK,"",attrInfo);
    }

    @RequestMapping("setAttrConfs")
    private Result saveAttrConfs(String attrConfId,String opions,String attrGroupId){
        System.out.println(attrConfId+opions+attrGroupId);
        UpdateWrapper<ProductAttrConf> productAttrConfUpdateWrapper = new UpdateWrapper<>();
        productAttrConfUpdateWrapper.eq("id",attrConfId);
        ProductAttrConf productAttrConf = new ProductAttrConf().setGroupId(attrGroupId).setOpions(opions);
        productAttrConfService.update(productAttrConf,productAttrConfUpdateWrapper);
        return new Result();
    }

}

