package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.woniuxy.entity.Member;
import com.woniuxy.mapper.MemberMapper;
import com.woniuxy.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.MemberVo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    //根据cityVo对象的值组织查询条件
    QueryWrapper<Member> Wrapper = new QueryWrapper<>();
    @Override
    public int addMember(Member member) {
//        System.out.println("进入了service");
        int i = memberMapper.insert(member);

        return i;
    }


    //根据id修改会员信息
    public int updateMember(Member member){
        int i = memberMapper.updateById(member);
        return i;
    }

    @Override
    public int deleteMember(ArrayList<String> ids) {
        int i = memberMapper.deleteBatchIds(ids);
        return i;
    }


}
