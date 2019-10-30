package com.zegobird.product.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.common.model.ErrorCode;
import com.zegobird.common.util.ApiAssert;
import com.zegobird.common.util.HttpClientUtil;
import com.zegobird.common.util.HttpResult;
import com.zegobird.common.util.MD5Util;
import com.zegobird.product.constant.RedisKey;
import com.zegobird.product.dto.ProductTypeDto;
import com.zegobird.product.dto.ProductTypeTree;
import com.zegobird.product.dto.CategoryDto;
import com.zegobird.product.entity.Producttype;
import com.zegobird.product.service.IProducttypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 产品类目 前端控制器
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@RestController
@RequestMapping(value = "/protype", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "产品类目", description = "产品类目操作接口")
public class ProducttypeController extends BasePageController {
    @Autowired
    private IProducttypeService typeService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("新增产品类目")
    @PostMapping("/add")
    @Transactional
    public String createType(@RequestBody @Validated({ProductTypeDto.Create.class}) ProductTypeDto dto) {
        Producttype producttype = dto.convert(Producttype.class);
        Integer maxLevelOrderNo = typeService.getMaxLevelOrderNo(producttype.getLevel());
        //todo: 判断maxLevelOrderNo 不能超过1000
        if (producttype.getLevel() < 3)
            ApiAssert.isTrue(new ErrorCode("当前一级或二级分类不能超过100个", 500, true, "当前一级或二级分类不能超过100个"), maxLevelOrderNo < 99);
        if (producttype.getLevel() >= 3)
            ApiAssert.isTrue(new ErrorCode("当前三级分类不能超过1000个", 500, true, "当前三级分类不能超过1000个"), maxLevelOrderNo < 999);
        producttype.setLevelorderno(maxLevelOrderNo + 1);
        Producttype producttype1 = typeService.getOne(new QueryWrapper<Producttype>().eq("typename", producttype.getTypename()));
        ApiAssert.isNull(new ErrorCode("存在同名的分类名称", 500, true, ""), producttype1);
        typeService.save(producttype);
        String fulltypeid = producttype.getLevel() == 1 ? producttype.getId().toString() : producttype.getFulltypeid() + "," + producttype.getId();
        String fulltypename = producttype.getLevel() == 1 ? producttype.getTypename() : producttype.getFulltypename() + "," + producttype.getTypename();
        producttype.setFulltypeid(fulltypeid);
        producttype.setFulltypename(fulltypename);
        typeService.updateById(producttype);
        return formatResponseParams(EXEC_OK, null);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据parentid获取下一级分类列表")
    public String getTypeList(@PathVariable("id") Integer parentid) {

//        Producttype producttype = typeService.getById(id);
//        ApiAssert.notNull(ErrorCodeEnum.ID_QUERY_DATA_NOT_FOUND,producttype);
        List<Producttype> subTypeList = typeService.getSubTypeList(parentid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", subTypeList);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @PutMapping("/{id}")
    @ApiOperation("更新产品分类")
    public String updateType(@RequestBody @Validated({ProductTypeDto.Update.class}) ProductTypeDto dto) {
        Producttype producttype = dto.convert(Producttype.class);
        typeService.saveOrUpdate(producttype);
        return formatResponseParams(EXEC_OK, null);
    }

    @GetMapping("/tree")
    @ApiOperation("获取产品分类树结构")
    public String GetTree() {
        String redisKey = RedisKey.PRODUCT_PRODUCTTYPE_TREE;
        List<ProductTypeTree> tree=new ArrayList<>();
        if (redisTemplate.hasKey(redisKey)) {
            System.out.println("查询缓存");
            tree = redisTemplate.opsForList().range(redisKey, 0, -1);

        } else {
            System.out.println("查询数据库");
             tree = typeService.getProductTypeTree();
             redisTemplate.opsForList().leftPushAll(redisKey,tree);
             redisTemplate.expire(redisKey,2, TimeUnit.HOURS);
        }

        JSONObject jsonobj = new JSONObject();
        jsonobj.put("res", tree);
        return formatResponseParams(EXEC_OK, jsonobj);
    }

    @PostMapping("/init")
    @ApiOperation("初始化产品分类数据")
    public String initProductType() throws Exception {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = MD5Util.md5("B1key", timestamp).toUpperCase();
        Map<String, String> map = new HashMap<>();
        map.put("time", timestamp);
        map.put("sign", sign);
        HttpResult httpResult = HttpClientUtil.doPost("https://devtest.zegobird.com:11111/apiseller/api/queryCategory", map);
        JSONObject jsonObject = new JSONObject();
        String resbody = httpResult.getBody();
        String datas = JSONObject.parseObject(resbody).getString("datas");
        List<CategoryDto> categoryList = JSONObject.parseArray(JSONObject.parseObject(datas).getString("categoryList"), CategoryDto.class);
        for (CategoryDto categoryDto : categoryList) {
            createProductTypeEntity(categoryDto);

        }
        return formatResponseParams(EXEC_OK, null);
    }

    /**
     * 获取父级id
     *
     * @param categoryDto
     * @return
     */
    private void createProductTypeEntity(CategoryDto categoryDto) {
        Producttype producttype = new Producttype();
        producttype.setLevel(categoryDto.getDeep());//类别等级
        producttype.setCategoryid(categoryDto.getCategoryId());//电商分类id
        producttype.setTypename(categoryDto.getCategoryName());//分类名称
        Integer maxLevelOrderNo = typeService.getMaxLevelOrderNo(producttype.getLevel());
        producttype.setLevelorderno(maxLevelOrderNo + 1);
        producttype.setState(1);
        String fulltypeid = "";
        String fulltypename = "";
        //producttype.getLevel()==1?producttype.getId().toString():producttype.getFulltypeid() + "," + producttype.getId();
        //producttype.getLevel()==1?producttype.getTypename():producttype.getFulltypename()+","+producttype.getTypename();
        if (categoryDto.getParentId() == 0) {//顶级节点，父级id为0
            producttype.setParentId(0);
        } else {
            //查询当前电商类型的父节点
            Producttype one = typeService.getOne(new QueryWrapper<Producttype>().eq("categoryid", categoryDto.getParentId()));
            producttype.setParentId(one.getId());
            fulltypeid = one.getFulltypeid() + ",";
            fulltypename = one.getFulltypename() + ",";
        }
        typeService.save(producttype);

        producttype.setFulltypeid(fulltypeid + producttype.getId());
        producttype.setFulltypename(fulltypename + producttype.getTypename());
        typeService.updateById(producttype);

    }
}

