package com.woniuxy.mapper;

import com.woniuxy.entity.Distributor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface DistributorMapper extends BaseMapper<Distributor> {


    @Update("UPDATE t_distributor set member_num = member_num+1 WHERE `name`=#{name}")
    int updateByName(String name);


}
