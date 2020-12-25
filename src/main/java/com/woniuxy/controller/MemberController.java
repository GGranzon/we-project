package com.woniuxy.controller;


import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.Distributor;
import com.woniuxy.entity.Member;
import com.woniuxy.mapper.DistributorMapper;
import com.woniuxy.service.DistributorService;
import com.woniuxy.service.MemberService;
import com.woniuxy.service.UserService;
import com.woniuxy.vo.MemberVo;
import com.woniuxy.vo.PageVo;
import com.woniuxy.vo.StatusVo;
import org.apache.commons.collections.ListUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
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
@RequestMapping("member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @Resource
    private DistributorService distributorService;
    @Resource
    private DistributorMapper distributorMapper;

    //新增会员
    @PostMapping("save")
    public Result addMember(@RequestBody Member member){
        System.out.println(member.toString());
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        member.setMemberId(id);
        int i = 0;
        try {
            i = memberService.addMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i>0){
            //新增成功的话，先查询渠道商
            QueryWrapper<Distributor> distributorQueryWrapper = new QueryWrapper<>();
            distributorQueryWrapper.eq("name",member.getDistributorId());
            Distributor distributor = distributorService.getOne(distributorQueryWrapper);
            if(!ObjectUtils.isEmpty(distributor)){//如果不为空，就要给相应的渠道商的会员数量添加1
                int j = distributorMapper.updateByName(distributor.getName());
                return new Result(true, StatusCode.OK,"新增会员成功");
            }else{//如果为空，返回该渠道商不存在
                return new Result(false, StatusCode.ISEMPTYDIS,"该渠道商不存在");
            }
        }else{
            return new Result(false, StatusCode.FAILED,"新增会员失败");
        }
    }
    //查询全部会员
    @GetMapping("all")
    public Result showAllMember(){
        List<Member> members = memberService.list(null);
        return new Result(true,StatusCode.OK,"查询所有会员列表成功",members);
    }
    //分页查询全部会员列表
    @GetMapping("page")
    public Result showMember(PageVo pageVo){
        // System.out.println(pageVo.getCurrent()+pageVo.getSize());
        Page<Member> memberPage = new Page<>(pageVo.getCurrent(), pageVo.getSize());
        IPage<Member> page = memberService.page(memberPage, null);
        System.out.println(page);
        return new Result(true,StatusCode.OK,"分页查询所有会员列表成功",page);
    }
    //根据id查询会员信息
    @GetMapping("findOne")
    public Result findOne(Member member){
        System.out.println(member.getMemberId());
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",member.getMemberId());
        Member one = memberService.getOne(queryWrapper);
        return new Result(true,StatusCode.OK,"查询单个会员信息成功",one);
    }
    //修改会员信息
    @GetMapping("update")
    public Result updateMember(Member member){
        System.out.println("更新页面的id"+member.getMemberId());
        int i = memberService.updateMember(member);
        if (i>0){
            return new Result(true,StatusCode.OK,"修改会员信息成功");
        }else{
            return new Result(false,StatusCode.FAILED,"修改会员信息失败");
        }
    }
    //删除会员信息
    @PostMapping("del")
    public Result deleteMember(@RequestBody ArrayList<String> ids){
        System.out.println(ids);
        if(ids.size()>0){
            //有选中的值
            int i = memberService.deleteMember(ids);
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
            Page<Member> memberPage = new Page<>(statusVo.getCurrent(), statusVo.getSize());
            IPage<Member> page = memberService.page(memberPage, null);
            return new Result(true,StatusCode.OK,"获取成功",page);
        }else{

}

