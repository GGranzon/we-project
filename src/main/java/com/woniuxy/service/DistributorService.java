package com.woniuxy.service;

import com.woniuxy.entity.Distributor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.entity.Member;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface DistributorService extends IService<Distributor> {
    int addDis(Distributor distributor);
    int updateDis(Distributor distributor);
    int deleteDis(ArrayList<String> ids);
}
