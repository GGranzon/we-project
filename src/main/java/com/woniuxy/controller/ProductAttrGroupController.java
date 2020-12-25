package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.ProductAttrConf;
import com.woniuxy.entity.ProductAttrGroup;
import com.woniuxy.entity.ProductCategory;
import com.woniuxy.mapper.ProductAttrGroupMapper;
import com.woniuxy.service.ProductAttrGroupService;
import com.woniuxy.vo.PageVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@RestController
@RequestMapping("/product-attr-group")
public class ProductAttrGroupController {

    @Resource
    private ProductAttrGroupService productAttrGroupService;

    @RequestMapping("getAttrGroupInfo")
    public Result findAttrGroupInfo(PageVo pageVo){
        System.out.println(pageVo);
        IPage<ProductAttrGroup> attrGroupInfo = productAttrGroupService.findAttrGroup(pageVo);
        System.out.println(attrGroupInfo);
        return new Result(true, StatusCode.OK,"成功",attrGroupInfo);
    }

    @RequestMapping("getCategorys")
    public Result findGategorys(){
        List<ProductCategory> categorys = productAttrGroupService.findCategorys();
        System.out.println(categorys);
        return new Result(true,StatusCode.OK,"成功",categorys);
    }

    @RequestMapping("addAttrGroup")
    public Result newAttrGroup(String groupName,String status,String sort,String categoryId){
        System.out.println(groupName+status+sort+categoryId);
        String msg = productAttrGroupService.newAttrGroup(groupName, status, sort, categoryId);
        return new Result(true,StatusCode.OK,msg);
    }

    @RequestMapping("updateAttrGroup")
    public Result alterAttrGroup(String groupId,String groupName,String status,String sort,String categoryId){
        System.out.println(groupId+groupName+status+sort+categoryId);
        String msg = productAttrGroupService.alterAttrGroup(groupId,groupName,status,sort,categoryId);
        return new Result(true,StatusCode.OK,msg);
    }

    @RequestMapping("deleteAttrGroup")
    public Result alterAttrGroup(@RequestBody String[] groupIds){
        String msg = productAttrGroupService.deleteAttrGroup(groupIds);
        return new Result(true,StatusCode.OK,msg);
    }

    @RequestMapping("fuzzyQueryGroup")
    public Result fuzzyAttrGroupInfo(PageVo pageVo,String fuzzy){
        System.out.println(fuzzy);
//        IPage<ProductAttrGroup> attrGroupInfo = productAttrGroupService.findAttrGroup(pageVo,fuzzy);
//        System.out.println(attrGroupInfo);
        IPage<ProductAttrGroup> productAttrGroupIPage = productAttrGroupService.fuzzyAttrGroup(pageVo, fuzzy);
        return new Result(true, StatusCode.OK,"成功",productAttrGroupIPage);
    }

}

