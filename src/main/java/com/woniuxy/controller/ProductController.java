package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.*;
import com.woniuxy.jwt.JwtUtil;
import com.woniuxy.mapper.*;
import com.woniuxy.service.ProductCategoryService;
import com.woniuxy.service.ProductService;
import com.woniuxy.vo.PageVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    private BrandinfoMapper brandInfoMapper ;

    @Resource
    private ShopMapper shopMapper ;
    @Resource
    private ProductMapper productMapper ;

    @Resource
    private IdentifyMapper identifyMapper;

    @Resource
    private ProductAttrConfMapper productAttrConfMapper;

    @Resource
    private BrandAndCategoryMapper brandAndCategoryMapper ;
    @Resource
    private GroupAndCategoryMapper groupAndCategoryMapper ;
    @Resource
    private ProductAttrGroupMapper productAttrGroupMapper ;
    @Resource
    private UserMapper userMapper ;
    @Resource
    private ProductImgMapper productImgMapper ;

    public String publicId;
    
    @RequestMapping("getProductInfo")
    private Result queryInfo (PageVo pageVo){
        System.out.println(pageVo);
        IPage<Product> productInfo = productService.queryProductInfo(pageVo);
        System.out.println(productInfo);
        return new Result(true, StatusCode.OK,"成功",productInfo);
    }


    @RequestMapping("deleteProduct")
    public Result deleteProductByid(@RequestBody String[] pids){
        for (int i=0;i < pids.length;i++){
            System.out.println(pids[i]);
            QueryWrapper<ProductImg> wrapper = new QueryWrapper<>();
            wrapper.eq("product_id",pids[i]);
            productImgMapper.delete(wrapper);
            productMapper.deleteById(pids[i]);
        }
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping("fuzzyProductInfo")
    private Result fuzzyInfo (PageVo pageVo,String productName,String productStatus,String username){
        System.out.println(pageVo);
        System.out.println(productName+productStatus+username);
        IPage<Product> productInfo = productService.fuzzyProductInfo(pageVo,productName,productStatus,username);
        System.out.println(productInfo);
        return new Result(true, StatusCode.OK,"成功",productInfo);
    }

    @RequestMapping("getCategorys")
    public Result findGategorys(){
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_level",2);
        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
        System.out.println(productCategories);
        return new Result(true,StatusCode.OK,"成功",productCategories);
    }


    @RequestMapping("getBrands")
    public Result findBrands(String categoryId){
        System.out.println(categoryId);
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        System.out.println("获取的分类"+productCategory);
        QueryWrapper<BrandAndCategory> bwrapper = new QueryWrapper<>();
        bwrapper.eq("category_id",productCategory.getCategoryParent());
        List<BrandAndCategory> brandAndCategorys = brandAndCategoryMapper.selectList(bwrapper);
        System.out.println("获取中间类"+brandAndCategorys);
        List<Brandinfo> brandInfos = new ArrayList<>();
        for (BrandAndCategory brandAndCategory:brandAndCategorys) {
            brandInfos.add(brandInfoMapper.selectById(brandAndCategory.getBrandId()));
        }
        System.out.println(brandInfos);
//        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
//        wrapper.eq("category_level",2);
//        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
//        System.out.println(productCategories);
        return new Result(true,StatusCode.OK,"成功",brandInfos);
    }

    @RequestMapping("getAttrGroups")
    private Result findAttrGroups(String categoryId){
        System.out.println(categoryId);
        System.out.println(categoryId);
        QueryWrapper<GroupAndCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",productCategoryMapper.selectById(categoryId).getCategoryParent());
        List<GroupAndCategory> groupAndCategories = groupAndCategoryMapper.selectList(wrapper);
        List<ProductAttrGroup> productAttrGroups = new ArrayList<>();
        for (GroupAndCategory groupAndCategory : groupAndCategories){
            QueryWrapper<ProductAttrGroup> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",groupAndCategory.getGroupId());
            productAttrGroups.add(productAttrGroupMapper.selectOne(wrapper1));
        }
        System.out.println(productAttrGroups);
        return new Result(true,StatusCode.OK,"成功",productAttrGroups);
    }

    @RequestMapping("getAttrConfs")
    private Result findAttrConfs(String attrGroupid){
        System.out.println(attrGroupid);
        QueryWrapper<ProductAttrConf> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",attrGroupid);
        List<ProductAttrConf> productAttrConfs = productAttrConfMapper.selectList(wrapper);
        System.out.println(productAttrConfs);
        return new Result(true,StatusCode.OK,"成功",productAttrConfs);
    }

    @RequestMapping("getShops")
    private Result findAttrConfs(){
        List<Shop> shops = shopMapper.selectList(null);
        System.out.println(shops);
        return new Result(true,StatusCode.OK,"成功",shops);
    }
    @RequestMapping("uploadFile")
    private Result upload(@RequestParam("file") MultipartFile[] imgFile, HttpServletRequest request) throws Exception{
        String[] imgInfos = new String[4];
        imgInfos[0] = "正面";
        imgInfos[1] = "背面";
        imgInfos[2] = "侧面";
        imgInfos[3] = "顶部";

            //设置上传文件存放的目录：target/classes/static/files
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
            System.out.println(realPath);
            //按日期存放上传文件的日期目录
            String dateDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //构建上传文件真实存放目录
            File uploadDir = new File(realPath,dateDir);
            if (!uploadDir.exists()) {//目录不存在时，创建
                uploadDir.mkdirs();
            }
        System.out.println(uploadDir.getAbsolutePath());
        for (int i=0;i<imgFile.length;i++){
            //获取上传文件的真实文件名
            String realName = imgFile[i].getOriginalFilename();
            System.out.println(realName);
            //设置上传文件前缀：时间戳+UUID
            String uploadFileNamePrefix=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+UUID.randomUUID().toString().replace("-","");
            //得到上传文件后缀
            String uploadFileNameSuffix = realName.substring(realName.lastIndexOf(".") + 1);;
            //构建上传到服务器的文件名
            String uploadFileName=uploadFileNamePrefix+"."+uploadFileNameSuffix;
            File file = new File(uploadDir, uploadFileName);

            System.out.println(file.getAbsolutePath());

            ProductImg productImg = new ProductImg();
            productImg.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            productImg.setProductId(this.publicId);
            productImg.setImg(file.getAbsolutePath());
            productImg.setImgInfo(imgInfos[i]);
            productImgMapper.insert(productImg);
            //将文件上传到服务器
            imgFile[i].transferTo(file);
        }
        return new Result(true,StatusCode.OK,"图片上传成功");
    }

    @RequestMapping("addProduct")
    private Result newProduct(@RequestBody Product product){
//        String username = JwtUtil.getUsername(SecurityUtils.getSubject().getPrincipal().toString());

        QueryWrapper<User> username1 = new QueryWrapper<>();
        username1.eq("username", "tom");
        User user = userMapper.selectOne(username1);

        ProductCategory productCategory = productCategoryMapper.selectById(product.getCategoryId());


        String pId = UUID.randomUUID().toString().replaceAll("-", "");
        publicId = pId;
        product.setProductId(pId);
        product.setInputUser(user.getId());
        product.setSurveyor(user.getId());
        product.setAssessor(user.getId());
        product.setSecondCatId(productCategory.getCategoryParent());
        product.setThirdCatId(product.getCategoryId());
        if (product.getDegree()!=null){
            product.setProductStatus("已鉴定");
        }else {
            product.setProductStatus("待鉴定");
        }
        int row = productMapper.insert(product);
        Identify identify = new Identify().setIdentifyId(UUID.randomUUID().toString().replaceAll("-", "")).setDegree(product.getDegree()).setNote(product.getIdentifyNote());
        int row1 = identifyMapper.insert(identify);
        if (row == 1 && row1 == 1){
            return new Result(true,StatusCode.OK,"新增成功");
        }else {
            return new Result(true,StatusCode.OK,"新增失敗");
        }

    }



}

