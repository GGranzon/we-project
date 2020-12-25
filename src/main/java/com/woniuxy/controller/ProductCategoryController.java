package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.ProductCategory;
import com.woniuxy.mapper.ProductCategoryMapper;
import com.woniuxy.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@CrossOrigin
@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    //查询所有
    @GetMapping("findAll")
    public Result queryProductCategoryAll(){
        List<ProductCategory> productCategoryList = null;
        List<ProductCategory> productCategories = productCategoryService.list(null);
        Iterator<ProductCategory> it = productCategories.iterator();
        while (it.hasNext()){
            ProductCategory productCategory = it.next();
            if(!productCategory.getCategoryParent().equals(null)){
                productCategoryList.add(productCategory);
            }
        }
        return new Result(true , StatusCode.OK , "查到啦" , productCategoryList);
    }

    //新增商品类别
    @PostMapping("add")
    public Result insertProductCategory(@RequestBody ProductCategory productCategory){
        System.out.println(productCategory.getCategoryName());
        productCategory.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        if(productCategory.getCategoryParent().equals(null)){
            productCategory.setCategoryLevel(1);
        }else{
            productCategory.setCategoryLevel(2);
        }
        boolean save = productCategoryService.save(productCategory);

        return new Result(true , StatusCode.OK , "新增成功！");
    }

    //分页查询大类
    @GetMapping("page")
    public Result queryPage(Integer current,Integer size,String like){
        System.out.println(like+"111");
        Page<ProductCategory> page = new Page<>(current,size);
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("category_parent");
        if(!like.equals(null)){
            queryWrapper.like("category_name",like);
        }
        IPage<ProductCategory> page1 = productCategoryService.page(page, queryWrapper);
        System.out.println(page1.getTotal());
        return new Result(true , StatusCode.OK , "查到啦，分页安排！", page1);
    }

    @GetMapping("smallPage")
    public Result queryPageSmall(Integer current,Integer size,String like){
        System.out.println(like+"111");
        Page<ProductCategory> page = new Page<>(current,size);
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_level","2");
        IPage<ProductCategory> page1 = productCategoryService.page(page, queryWrapper);
        System.out.println(page1.getTotal());
        return new Result(true , StatusCode.OK , "商品小类分页查到啦，分页安排！", page1);
    }

    //模糊查询
    @GetMapping("like")
    public Result queryLike(String like){
        System.out.println(like);
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("category_name",like);
        List<ProductCategory> productCategories = productCategoryService.list(queryWrapper);
        return new Result(true , StatusCode.OK , "模糊查询查到啦！" , productCategories);
    }

    //商品小类等模糊查询
    @GetMapping("like2")
    public Result queryLike2(String like){
        System.out.println(like);
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("category_name",like);
        queryWrapper.eq("category_level","2");
        List<ProductCategory> productCategories = productCategoryService.list(queryWrapper);
        return new Result(true , StatusCode.OK , "查到啦！" , productCategories);
    }

    //删除商品大类
    @PostMapping("byebye")
    public Result deleteType(@RequestBody List<ProductCategory> productCategorys){
        Iterator<ProductCategory> it = productCategorys.iterator();
        while (it.hasNext()){
            String id = it.next().getId();
            boolean b = productCategoryService.removeById(id);
            System.out.println(0);
        }
        return new Result(true , StatusCode.OK , "删除商品大类成功！");
    }

    //修改商品大类
    @PostMapping("update")
    public Result updateType(@RequestBody ProductCategory productCategory){
        boolean b = productCategoryService.updateById(productCategory);
        System.out.println(b);

        return new Result(true , StatusCode.OK , "修改成功了！");

    }

}

