package com.woniuxy.controller;


import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.Member;
import com.woniuxy.service.MemberService;
import com.woniuxy.service.UserService;
import com.woniuxy.vo.PageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
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
            return new Result(true, StatusCode.OK,"新增会员成功");
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
        System.out.println("11111");
        System.out.println(pageVo.getCurrent()+pageVo.getSize());
        Page<Member> memberPage = new Page<>(pageVo.getCurrent(), pageVo.getSize());
        IPage<Member> page = memberService.page(memberPage, null);
        System.out.println(page);
        return new Result(true,StatusCode.OK,"分页查询所有会员列表成功",page);
    }


}

