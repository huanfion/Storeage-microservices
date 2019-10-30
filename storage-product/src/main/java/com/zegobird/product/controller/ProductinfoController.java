package com.zegobird.product.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.common.enums.ErrorCodeEnum;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.common.util.ApiAssert;
import com.zegobird.product.dto.*;
import com.zegobird.product.entity.*;
import com.zegobird.product.feign.WarehouseClient;
import com.zegobird.product.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品信息 前端控制器
 * </p>
 *
 * @author huanfion
 * @since 2019-09-05
 */
@RestController
@RequestMapping(value = "/productinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "产品信息", description = "产品操作相关接口")
public class ProductinfoController extends BasePageController {

    @Autowired
    private IProductinfoService productService;
    @Autowired
    private IProducttypespecService typespecService;
    @Autowired
    /**
     * 产品 规格项服务
     */
    private IProductinfospecrelationService productspecService;

    @Autowired
    private IProductinfospecvaluerelatioService productspecvalueService;
    @Autowired
    private IProductattributeinfoService proatrrService;

    @Autowired
    private IProductinfospecvalueService skuService;

    @Autowired
    WarehouseClient warehouseClient;
    @GetMapping("/page")
    @ApiOperation("分页获取产品信息列表")
    public String getProductList(ProductQuery query) {
        IPage<ProductInfoSkuDto> productSkuList = productService.getProductSkuList(this.getPage(), query);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", productSkuList);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据产品id获取详细信息，包含sku 列表信息")
    public String getProductSkuDetail(@PathVariable("id") Integer id) {
        ProductwithSkus productSkuDetail = productService.getProductSkuDetail(id);
        productSkuDetail.setBatchAttrRule(warehouseClient.getBatchattrrulesByProductType(productSkuDetail.getProductdto().getTypeid()));
        //获取产品类型对应的规格项和规格值
        productSkuDetail.setProducttypespecwithValueList(typespecService.getProtypespecwithValue(productSkuDetail.getProductdto().getTypeid(), true));
        //获取产品规格项和规格值
        productSkuDetail.setProductSpecwithValueList(productspecService.getProductSpecWithValue(productSkuDetail.getProductdto().getProductid()));
        //获取产品类型对应的属性项和属性值
        productSkuDetail.setProducttypeattrwithValueList(typespecService.getProtypespecwithValue(productSkuDetail.getProductdto().getTypeid(), false));
        //获取产品属性列表
        productSkuDetail.setProAttributeInfoList(proatrrService.getProAttributeList(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", productSkuDetail);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @GetMapping("/sku")
    @ApiOperation("根据sku获取产品信息")
    public String getProductInfo(@RequestParam("sku")String sku){
        //Productinfospecvalue skudetail = skuService.getOne(new QueryWrapper<Productinfospecvalue>().eq("productspeccode", sku));
        ProductwithSkus productSkuDetailBySku = productService.getProductSkuDetailBySku(sku);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", productSkuDetailBySku);
        return formatResponseParams(EXEC_OK, jsonObject);
    }
    @GetMapping("/typespec/{typeid}")
    @ApiOperation("根据产品类型id获取产品类型规格项和规格值")
    public String getProductTypeSpecValue(@PathVariable("typeid") Long typeid) {
        List<ProducttypespecwithValueDto> protypespecwithValueList = typespecService.getProtypespecwithValue(typeid, true);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", protypespecwithValueList);
        return formatResponseParams(EXEC_OK, jsonObject);

    }

    @GetMapping("/attrspec/{typeid}")
    @ApiOperation("根据产品类型id获取产品类型属性项和属性值")
    public String getProductTypeAttrSpecValue(@PathVariable("typeid") Long typeid) {
        List<ProducttypespecwithValueDto> protypespecwithValueList = typespecService.getProtypespecwithValue(typeid, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", protypespecwithValueList);
        return formatResponseParams(EXEC_OK, jsonObject);

    }

    @GetMapping("/spec/{id}")
    @ApiOperation("根据产品id获取产品的规格项和规格值")
    public String getProductSpecAndValue(@PathVariable("id") Long productid) {
        List<ProductSpecwithValueDto> productSpecWithValue = productspecService.getProductSpecWithValue(productid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", productSpecWithValue);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @GetMapping("/attr/{id}")
    @ApiOperation("根据产品id获取产品的属性列表")
    public String getProductAttrs(@PathVariable("id") Integer productid) {
        List<ProAttrInfoDto> attributeList = proatrrService.getProAttributeList(productid);
        List<ProAttrInfoDto> attrInfoDtos = BeanConverter.convert(ProAttrInfoDto.class, attributeList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", attrInfoDtos);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @GetMapping("/{id}/skulist")
    @ApiOperation("根据产品id查看sku列表数据")
    public String getSkuListByProductId(@PathVariable("id") Integer productid){
        List<Productinfospecvalue> skulist = skuService.list(new QueryWrapper<Productinfospecvalue>().eq("productid", productid));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", skulist);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @PostMapping("/add")
    @ApiOperation("添加产品信息")
    @Transactional(rollbackFor = Exception.class)
    public String createProduct(@RequestBody @Validated({ProductMainDto.Create.class}) ProductMainDto dto) {
        Productinfo productinfo = dto.getProductdto().convert(Productinfo.class);
        productinfo.setProductimage(JSONArray.parseArray(dto.getProductdto().getProductimage()));
        //生成spu码
        String spuPrefix = GeneratorSpu(productinfo.getFulltypeid());
        //获取当前分类下最大号
        String maxSpu = productService.getMaxProductCode(spuPrefix);
        String productCode = "";
        if (StringUtils.isEmpty(maxSpu)) {
            productCode = spuPrefix + "000001";
        } else {
            productCode = spuPrefix +
                    StringUtils.leftPad(String.valueOf(Long.valueOf(maxSpu.substring(7)) + 1), 6, "0");
        }
        productinfo.setProductcode(productCode);
        productService.save(productinfo);
        // 添加记录 产品 规格项和值
        List<ProductSpecwithValueDto> productSpecwithValueList = dto.getProductSpecwithValueList();
        productSpecwithValueList.forEach(e -> e.setProductid(productinfo.getProductid()));
        productspecService.saveProductSpecWithValue(productSpecwithValueList);

        //添加记录 产品属性表
        List<ProAttrInfoDto> proAttributeInfoList = dto.getProAttributeInfoList();
        proAttributeInfoList.forEach(e -> e.setProductid(productinfo.getProductid()));
        List<Productattributeinfo> productattributeinfoList = BeanConverter.convert(Productattributeinfo.class, proAttributeInfoList);
        proatrrService.saveBatch(productattributeinfoList);

        //添加sku表记录
        List<ProductSepcValueDto> productSepcValueList = dto.getProductSkuList();
        //生成sku码
       int index=0;
        for (ProductSepcValueDto e : productSepcValueList) {
            ++index;
            e.setProductid(productinfo.getProductid());
            e.setProductspeccode(productinfo.getProductcode()+StringUtils.leftPad(String.valueOf(index),2,"0"));
        };
        //转成sku实体类型list
        List<Productinfospecvalue> productinfospecvalueList = BeanConverter.convert(Productinfospecvalue.class, productSepcValueList);

        skuService.saveBatch(productinfospecvalueList);
        return formatResponseParams(EXEC_OK, null);
    }

    @ApiOperation("保存产品信息")
    @PutMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public String saveProduct(@RequestBody @Validated({ProductMainDto.Update.class}) ProductMainDto dto) {
        //判断当前productid数据是否存在
        Long productid=dto.getProductdto().getProductid();
        ApiAssert.notNull(ErrorCodeEnum.ID_QUERY_DATA_NOT_FOUND,productService.getById(productid));
        Productinfo productinfo = dto.getProductdto().convert(Productinfo.class);
        productService.updateById(productinfo);

        /**
         * 更新（删除后添加）产品规格项和值
         */
        //1.查询当前产品的产品规格项列表
        List<Productinfospecrelation> productinfospecrelations =
                productspecService.list(new QueryWrapper<Productinfospecrelation>().eq("productid", productid));
        //2.遍历规格项列表，删除对应的产品规格值表数据（从表）
        for (Productinfospecrelation productinfospecrelation : productinfospecrelations) {
            productspecvalueService.remove(new QueryWrapper<Productinfospecvaluerelatio>().eq("productspecid",productinfospecrelation.getProductspecid()));
        }
        //3.删除产品规格项表（主表）
        productspecService.remove(new QueryWrapper<Productinfospecrelation>().eq("productid",productid));
        //4.新增产品规格项和产品规格值表
        List<ProductSpecwithValueDto> productSpecwithValueList = dto.getProductSpecwithValueList();
        productspecService.saveProductSpecWithValue(productSpecwithValueList);

        /**
         * 更新产品sku表
         */
        //删除sku表数据
        skuService.remove(new QueryWrapper<Productinfospecvalue>().eq("productid",productid));
        //添加sku表数据
        List<ProductSepcValueDto> productSepcValueList = dto.getProductSkuList();
        //生成sku码
        int index=0;
        for (ProductSepcValueDto e : productSepcValueList) {
            ++index;
            e.setProductid(productinfo.getProductid());
            e.setProductspeccode(productinfo.getProductcode()+StringUtils.leftPad(String.valueOf(index),2,"0"));
        };
        //转成sku实体类型list
        List<Productinfospecvalue> productinfospecvalueList = BeanConverter.convert(Productinfospecvalue.class, productSepcValueList);
        skuService.saveBatch(productinfospecvalueList);

        /**
         * 更新产品属性表
         */
        proatrrService.remove(new QueryWrapper<Productattributeinfo>().eq("productid",productid));
        //添加记录 产品属性表
        List<ProAttrInfoDto> proAttributeInfoList = dto.getProAttributeInfoList();
        List<Productattributeinfo> productattributeinfoList = BeanConverter.convert(Productattributeinfo.class, proAttributeInfoList);
        proatrrService.saveBatch(productattributeinfoList);
        return formatResponseParams(EXEC_OK, null);
    }

    /**
     * 生成产品SPU 码
     *
     * @param fulltypeid
     * @return
     */
    private String GeneratorSpu(String fulltypeid) {
        String[] typeidArr = fulltypeid.split(",");
        StringBuilder sbspu = new StringBuilder();
        int typelength = typeidArr.length;
        String typeidstr = "";
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                typeidstr = i < typelength ? typeidArr[i] : "000";
                sbspu.append(StringUtils.leftPad(typeidstr, 3, "0"));
            } else {
                typeidstr = i < typelength ? typeidArr[i] : "00";
                sbspu.append(StringUtils.leftPad(typeidstr, 2, "0"));
            }

        }
        return sbspu.toString();
    }
}

