package com.woniuxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.ProductAttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.entity.ProductCategory;
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
public interface ProductAttrGroupService extends IService<ProductAttrGroup> {
    IPage<ProductAttrGroup> findAttrGroup(PageVo pageVo);

    List<ProductCategory> findCategorys();

    String newAttrGroup(String groupName,String status,String sort,String categoryId);

    String alterAttrGroup(String groupId,String groupName,String status,String sort,String categoryId);

    String deleteAttrGroup(String[] groupIds);

    IPage<ProductAttrGroup> fuzzyAttrGroup(PageVo pageVo, String fuzzy);
}
