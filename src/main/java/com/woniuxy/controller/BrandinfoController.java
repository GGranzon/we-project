package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.Brandinfo;
import com.woniuxy.entity.ProductCategory;
import com.woniuxy.service.BrandinfoService;
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
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/brandinfo")
public class BrandinfoController {

    @Resource
    private BrandinfoService brandinfoService;
    //分页查询以及模糊查询品牌
    @GetMapping("page")
    public Result queryPage(Integer current, Integer size, String like){
        System.out.println(like);
        Page<Brandinfo> page = new Page<>(current,size);
        QueryWrapper<Brandinfo> queryWrapper = new QueryWrapper<>();
        if(!like.equals(null)){
            queryWrapper.like("brand_name",like).or().like("brand_initial",like);
        }
        IPage<Brandinfo> page1 = brandinfoService.page(page, queryWrapper);
        System.out.println(page1.getTotal());
        return new Result(true , StatusCode.OK , "查到啦，分页安排！", page1);
    }

    //修改商品大类
    @PostMapping("update")
    public Result updateType(@RequestBody Brandinfo brandinfo){
        boolean b = brandinfoService.updateById(brandinfo);
        System.out.println(b);

        return new Result(true , StatusCode.OK , "修改成功了！");

    }

    //删除商品大类
    @PostMapping("byebye")
    public Result deleteType(@RequestBody List<Brandinfo> brandinfos){
        Iterator<Brandinfo> it = brandinfos.iterator();
        while (it.hasNext()){
            String id = it.next().getId();
            boolean b = brandinfoService.removeById(id);
            System.out.println(0);
        }
        return new Result(true , StatusCode.OK , "删除商品大类成功！");
    }

    //新增品牌
    //新增商品类别
    @PostMapping("add")
    public Result insertBrandInfo(@RequestBody Brandinfo brandinfo){
       brandinfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        boolean save = brandinfoService.save(brandinfo);
        return new Result(true , StatusCode.OK , "新增成功！");
    }
}

