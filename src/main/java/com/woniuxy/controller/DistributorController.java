package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.Distributor;
import com.woniuxy.entity.Member;
import com.woniuxy.service.DistributorService;
import com.woniuxy.vo.PageVo;
import com.woniuxy.vo.StatusVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RestController
@RequestMapping("/distributor")
public class DistributorController {
    @Resource
    private DistributorService distributorService;

    //根据姓名查询会员是否存在
    @GetMapping("isEmptyDis")
    public Result findByName(String name){
        System.out.println(name);
        QueryWrapper<Distributor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        List<Distributor> one = distributorService.list(queryWrapper);
        if(one.size()==0){//渠道商不存在，可以注册
            return new Result(true, StatusCode.OK,"渠道商不存在");

        }else {
            return new Result(false,StatusCode.ISNOTEMPTY,"渠道商已存在");
        }
    }
    //新增会员
    @PostMapping("save")
    public Result addDis(@RequestBody Distributor distributor){
        System.out.println(distributor.toString());
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        distributor.setId(id);
        int i = 0;
        try {
            i = distributorService.addDis(distributor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i>0){
            return new Result(true, StatusCode.OK,"新增渠道商成功");
        }else{
            return new Result(false, StatusCode.FAILED,"新增渠道商失败");
        }
    }
    //分页查询全部会员列表
    @GetMapping("page")
    public Result showDis(PageVo pageVo){
        // System.out.println(pageVo.getCurrent()+pageVo.getSize());
        Page<Distributor> disPage = new Page<>(pageVo.getCurrent(), pageVo.getSize());
        IPage<Distributor> page = distributorService.page(disPage, null);
        System.out.println(page);
        return new Result(true,StatusCode.OK,"分页查询所有渠道商列表成功",page);
    }
    //根据id查询会员信息
    @GetMapping("findOne")
    public Result findOne(Distributor distributor){
        System.out.println(distributor.getId());
        QueryWrapper<Distributor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",distributor.getId());
        Distributor one = distributorService.getOne(queryWrapper);
        return new Result(true,StatusCode.OK,"查询单个渠道商信息成功",one);
    }
    //修改会员信息
    @GetMapping("update")
    public Result updateDis(Distributor distributor){
        System.out.println("更新页面的id"+distributor.getId());
        int i = distributorService.updateDis(distributor);
        if (i>0){
            return new Result(true,StatusCode.OK,"修改渠道商信息成功");
        }else{
            return new Result(false,StatusCode.FAILED,"修改渠道商信息失败");
        }
    }
    //删除会员信息
    @PostMapping("del")
    public Result deleteDis(@RequestBody ArrayList<String> ids){
        System.out.println(ids);
        if(ids.size()>0){
            //有选中的值
            int i = distributorService.deleteDis(ids);
            if(i>0){
                return new Result(true,StatusCode.OK,"删除成功");
            }else{
                return new Result(false,StatusCode.FAILED,"删除失败");
            }
        }else{
            return new Result(false,StatusCode.NULL,"请选中要删除的信息");
        }
    }
    //根据状态查询会员信息
    @GetMapping("status")
    public Result findByStatus(StatusVo statusVo){
        String sat = statusVo.getSta();
        if (sat.equals("全部")){
            Page<Distributor> disPage = new Page<>(statusVo.getCurrent(), statusVo.getSize());
            IPage<Distributor> page = distributorService.page(disPage, null);
            return new Result(true,StatusCode.OK,"获取成功",page);
        }else{
            QueryWrapper<Distributor> wrapper = new QueryWrapper<>();
            wrapper.eq("status",sat);
            //List<Member> list = memberService.list(wrapper);

            Page<Distributor> disPage = new Page<>(statusVo.getCurrent(), statusVo.getSize());
            Page<Distributor> page = distributorService.page(disPage, wrapper);
            return new Result(true,StatusCode.OK,"获取成功",page);
        }
    }
    //根据手机号/会员名查询会员信息
    @GetMapping("condition")
    public Result findByCondition(StatusVo statusVo){
        String condition = statusVo.getCondition();
        System.out.println(condition);
        QueryWrapper<Distributor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",condition).or().eq("tel",condition);
        Page<Distributor> page = new Page<>(statusVo.getCurrent(), statusVo.getSize());
        Page<Distributor> list = distributorService.page(page, queryWrapper);

        return new Result(true,StatusCode.OK,"获取成功",list );
    }
}

