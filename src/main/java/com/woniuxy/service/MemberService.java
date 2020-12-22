package com.woniuxy.service;

import com.woniuxy.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.vo.PageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface MemberService extends IService<Member> {

    int addMember(Member member);


}
