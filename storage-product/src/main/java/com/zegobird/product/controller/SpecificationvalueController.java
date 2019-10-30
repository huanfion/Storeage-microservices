package com.zegobird.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.product.dto.ProductTypeDto;
import com.zegobird.product.dto.SpecificationvalueDto;
import com.zegobird.product.dto.SpecificationvalueMainDto;
import com.zegobird.product.entity.*;
import com.zegobird.product.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author huanfion
 * @since 2019-09-04
 */
@RestController
@Api(value = "规格值信息信息", description = "规格值信息操作相关接口")
@RequestMapping(value="/specificationvalue",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpecificationvalueController extends BasePageController {
    @Autowired
    private ISpecificationvalueService iSpecificationvalueService;

    @Autowired
    private IProducttypeService iProducttypeService;

    @Autowired
    private IProducttypespecService iProducttypespecService;

    @Autowired
    private ISpecificationService iSpecificationService;

    @Autowired
    private IProducttypespecvalueService iProducttypespecvalueService;

    @GetMapping("/page")
    @ApiModelProperty("规格项对应的规格值分页信息列表")
    public String getSpecificationvaluePageList(@RequestParam(value = "specificationid", required = false) Integer specificationid){
        IPage<Specificationvalue> spcificationvaluePageList= iSpecificationvalueService.getSpecificationvaluePageList(this.getPage(),specificationid);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("res",spcificationvaluePageList);
        return formatResponseParams(EXEC_OK,jsonObject);
    }

    @PostMapping("/add")
    @ApiOperation("添加规格值信息")
    @Transactional
    public String addSpecificationvalue(@RequestBody @Validated({SpecificationvalueMainDto.Create.class}) SpecificationvalueMainDto specificationvalueMainDto){
        SpecificationvalueDto specificationvalueDto = specificationvalueMainDto.getSpecificationvalueDto();
        Specificationvalue specificationvalue = specificationvalueDto.convert(Specificationvalue.class);
        boolean s = iSpecificationvalueService.save(specificationvalue);
        Specification specification = iSpecificationService.getById(specificationvalueDto.getSpecificationid());
        if(s){
            //Integer[] typeids = specificationvalueMainDto.getTypeids();
            //List<Producttype> list= iProducttypeService.getTypeListbyIds(typeids);
            List<ProductTypeDto> productTypeDtos = specificationvalueMainDto.getProductTypeDtos();
            if(productTypeDtos.size()>0) {
                for (int i = 0; i < productTypeDtos.size(); i++) {
                    ProductTypeDto productTypeDto=productTypeDtos.get(i);
                    Producttypespec producttypespec = iProducttypespecService.getProducttypespec(specificationvalue.getSpecificationid(),productTypeDto.getId());//获取类目规格项ID
                    Producttypespecvalue producttypespecvalue = new Producttypespecvalue();
                    producttypespecvalue.setTypespecid(producttypespec.getTypespecid());
                    producttypespecvalue.setSpecificationid(specificationvalueDto.getSpecificationid());
                    producttypespecvalue.setSpecname(specification.getSpecificationname());
                    producttypespecvalue.setSpecvalueid(specificationvalue.getSpecvalueId());
                    producttypespecvalue.setSpecvalue(specificationvalueDto.getSpecvalue());
                    producttypespecvalue.setTypeid(productTypeDto.getId());
                    producttypespecvalue.setTypename(productTypeDto.getTypename());
                    producttypespecvalue.setFulltypeid(productTypeDto.getFulltypeid());
                    producttypespecvalue.setFulltypename(productTypeDto.getFulltypename());
                    iProducttypespecvalueService.save(producttypespecvalue);
                }
            }
        }
        return formatResponseParams(EXEC_OK, null);
    }

    @GetMapping("/delete/{id}")
    @ApiModelProperty("删除规格值信息")
    public String deleteSpecificationvalue(@PathVariable("id") Integer id){
        boolean b = iSpecificationvalueService.removeById(id);
        if(b) {
            return formatResponseParams(EXEC_OK, null);
        }else{
            return formatResponseParams(EXEC_ERROR, null);
        }
    }
}


