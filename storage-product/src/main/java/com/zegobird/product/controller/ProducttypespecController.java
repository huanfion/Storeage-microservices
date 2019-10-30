package com.zegobird.product.controller;


import com.alibaba.fastjson.JSONObject;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.product.dto.ProducttypespecwithValueDto;
import com.zegobird.product.service.IProducttypespecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 类目规格属性 前端控制器
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@RestController
@RequestMapping("/producttypespec")
public class ProducttypespecController extends BasePageController {
    @Autowired
    private IProducttypespecService iProducttypespecService;
    @GetMapping("/getSpecwithValue")
    @ApiModelProperty("根据类别获取规格项及相对应的规格值，true为规格，false为属性")
    public String getProtypespecwithValue(Long typeid,Boolean issaleprop){
        List<ProducttypespecwithValueDto> producttypespecwithValue= iProducttypespecService.getProtypespecwithValue(typeid,issaleprop);//issaleprop:true规格；false属性
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("res",producttypespecwithValue);
        return formatResponseParams(EXEC_OK,jsonObject);
    }
}

