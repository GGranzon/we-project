package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.GroupAndCategory;
import com.woniuxy.entity.ProductAttrConf;
import com.woniuxy.entity.ProductAttrGroup;
import com.woniuxy.entity.ProductCategory;
import com.woniuxy.mapper.GroupAndCategoryMapper;
import com.woniuxy.mapper.ProductAttrGroupMapper;
import com.woniuxy.mapper.ProductCategoryMapper;
import com.woniuxy.service.ProductAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.service.ProductAttrService;
import com.woniuxy.vo.PageVo;
import org.springframework.stereotype.Service;

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
@Service("productAttrGroupService")
public class ProductAttrGroupServiceImpl extends ServiceImpl<ProductAttrGroupMapper, ProductAttrGroup> implements ProductAttrGroupService {

    @Resource
    private ProductAttrGroupMapper productAttrGroupMapper;

    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private GroupAndCategoryMapper groupAndCategoryMapper;

    @Override
    public IPage<ProductAttrGroup> findAttrGroup(PageVo pageVo) {
        Page<ProductAttrGroup> page = new Page<>(pageVo.getCurrent(),pageVo.getSize());
        IPage<ProductAttrGroup> attrInfo = productAttrGroupMapper.findAttrGroup(page);
        return attrInfo;
    }

    @Override
    public List<ProductCategory> findCategorys() {
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_level","1");
        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
        return productCategories;
    }

    @Override
    public String newAttrGroup(String groupName, String status, String sort, String categoryId) {
        ProductAttrGroup productAttrGroup = new ProductAttrGroup();
        String groupId = UUID.randomUUID().toString().replaceAll("-", "");
        productAttrGroup.setId(groupId);
        productAttrGroup.setGroupName(groupName);
        productAttrGroup.setStatus(status);
        productAttrGroup.setSort(sort);
        int row = productAttrGroupMapper.insert(productAttrGroup);
        GroupAndCategory groupAndCategory = new GroupAndCategory();
        groupAndCategory.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        groupAndCategory.setCategoryId(categoryId);
        groupAndCategory.setGroupId(groupId);
        int row1 = groupAndCategoryMapper.insert(groupAndCategory);

        if (row==1 && row1==1){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @Override
    public String alterAttrGroup(String groupId,String groupName, String status, String sort, String categoryId) {
        ProductAttrGroup productAttrGroup = new ProductAttrGroup();
        productAttrGroup.setGroupName(groupName);
        productAttrGroup.setStatus(status);
        productAttrGroup.setSort(sort);
        UpdateWrapper<ProductAttrGroup> groupUpdateWrapper = new UpdateWrapper<>();
        groupUpdateWrapper.eq("id",groupId);
        int row = productAttrGroupMapper.update(productAttrGroup,groupUpdateWrapper);
        GroupAndCategory groupAndCategory = new GroupAndCategory();
        groupAndCategory.setGroupId(groupId);
        groupAndCategory.setCategoryId(categoryId);
        UpdateWrapper<GroupAndCategory> categoryUpdateWrapper = new UpdateWrapper<>();
        categoryUpdateWrapper.eq("group_id",groupId);
        int row1 = groupAndCategoryMapper.update(groupAndCategory,categoryUpdateWrapper);
        if (row==1 && row1==1){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public String deleteAttrGroup(String[] groupIds) {
        for (int i = 0;i<groupIds.length;i++){
            int row = 0;
            int row1 = 0;
            UpdateWrapper<ProductAttrGroup> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",groupIds[i]);
            row = productAttrGroupMapper.delete(wrapper);
            UpdateWrapper<GroupAndCategory> gacWrapper = new UpdateWrapper<>();
            gacWrapper.eq("group_id",groupIds[i]);
            row1 = groupAndCategoryMapper.delete(gacWrapper);
            if (row==0 && row1==0){
                return "删除失败";
            }
        }
            return "删除成功";
    }

    @Override
    public IPage<ProductAttrGroup> fuzzyAttrGroup(PageVo pageVo, String fuzzy) {
        Page<ProductAttrGroup> page = new Page<>(pageVo.getCurrent(),pageVo.getSize());
        QueryWrapper<ProductAttrGroup> wrapper = new QueryWrapper<>();
        wrapper.like("group_name",fuzzy);
        wrapper.eq("pag.deleted",0);
        IPage<ProductAttrGroup> productAttrGroupIPage = productAttrGroupMapper.fuzzyAttrGroup(page, wrapper);
        return productAttrGroupIPage;
    }
}
