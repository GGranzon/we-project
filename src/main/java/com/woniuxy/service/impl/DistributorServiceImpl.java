package com.woniuxy.service.impl;

import com.woniuxy.entity.Distributor;
import com.woniuxy.entity.Member;
import com.woniuxy.mapper.DistributorMapper;
import com.woniuxy.service.DistributorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class DistributorServiceImpl extends ServiceImpl<DistributorMapper, Distributor> implements DistributorService {

    @Resource
    private DistributorMapper distributorMapper;
    @Override
    public int addDis(Distributor distributor) {
        int i = distributorMapper.insert(distributor);
        return i;
    }

    @Override
    public int updateDis(Distributor distributor) {
        int i = distributorMapper.updateById(distributor);
        return i;
    }
    //删除
    @Override
    public int deleteDis(ArrayList<String> ids) {
        int i = distributorMapper.deleteBatchIds(ids);
        return i;
    }


}
