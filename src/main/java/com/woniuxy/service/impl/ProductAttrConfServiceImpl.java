package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.ProductAttrConf;
import com.woniuxy.mapper.ProductAttrConfMapper;
import com.woniuxy.service.ProductAttrConfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-22
 */
@Service("productAttrConfService")
public class ProductAttrConfServiceImpl extends ServiceImpl<ProductAttrConfMapper, ProductAttrConf> implements ProductAttrConfService {

    @Resource
    private ProductAttrConfMapper productAttrConfMapper;

    @Override
    public IPage<ProductAttrConf> findAttrInfo(PageVo pageVo) {
        Page<ProductAttrConf> page = new Page<>(pageVo.getCurrent(),pageVo.getSize());
        IPage<ProductAttrConf> attrInfo = productAttrConfMapper.findAttrInfo(page);
        return attrInfo;
    }
}
