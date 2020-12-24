package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.*;
import com.woniuxy.mapper.*;
import com.woniuxy.service.ProductCategoryService;
import com.woniuxy.service.ProductService;
import com.woniuxy.vo.PageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    private BrandInfoMapper brandInfoMapper ;

    @Resource
    private ProductAttrConfMapper productAttrConfMapper;

    @Resource
    private BrandAndCategoryMapper brandAndCategoryMapper ;
    @Resource
    private GroupAndCategoryMapper groupAndCategoryMapper ;
    
    @RequestMapping("getProductInfo")
    private Result queryInfo (PageVo pageVo){
        System.out.println(pageVo);
        IPage<Product> productInfo = productService.queryProductInfo(pageVo);
        System.out.println(productInfo);
        return new Result(true, StatusCode.OK,"成功",productInfo);
    }

    @RequestMapping("getCategorys")
    public Result findGategorys(){
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_level",2);
        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
        System.out.println(productCategories);
        return new Result(true,StatusCode.OK,"成功",productCategories);
    }

    @RequestMapping("getBrands")
    public Result findBrands(String categoryId){
        System.out.println(categoryId);
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        System.out.println("获取的分类"+productCategory);
        QueryWrapper<BrandAndCategory> bwrapper = new QueryWrapper<>();
        bwrapper.eq("category_id",productCategory.getCategoryParent());
        List<BrandAndCategory> brandAndCategorys = brandAndCategoryMapper.selectList(bwrapper);
        System.out.println("获取中间类"+brandAndCategorys);
        List<BrandInfo> brandInfos = new ArrayList<>();
        for (BrandAndCategory brandAndCategory:brandAndCategorys) {
            brandInfos.add(brandInfoMapper.selectById(brandAndCategory.getBrandId()));
        }
        System.out.println(brandInfos);
//        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
//        wrapper.eq("category_level",2);
//        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
//        System.out.println(productCategories);
        return new Result(true,StatusCode.OK,"成功",brandInfos);
    }

    @RequestMapping("/getAttrConfs")
    private Result findAttrConfs(String categoryId){
        System.out.println(categoryId);
        QueryWrapper<GroupAndCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",productCategoryMapper.selectById(categoryId).getCategoryParent());
        List<GroupAndCategory> groupAndCategories = groupAndCategoryMapper.selectList(wrapper);
        List<ProductAttrConf> productAttrConfs = new ArrayList<>();
        for (GroupAndCategory groupAndCategory: groupAndCategories){
            QueryWrapper<ProductAttrConf> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("group_id",groupAndCategory.getGroupId());
            List<ProductAttrConf> productAttrConfs1 = productAttrConfMapper.selectList(wrapper1);
            for (ProductAttrConf productAttrConf: productAttrConfs1){
                productAttrConfs.add(productAttrConf);
            }

        }
        System.out.println(productAttrConfs);
        return new Result(true,StatusCode.OK,"成功",productAttrConfs);
    }




}

