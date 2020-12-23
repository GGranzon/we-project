package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.Member;
import com.woniuxy.mapper.MemberMapper;
import com.woniuxy.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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


    //分页查询




}
