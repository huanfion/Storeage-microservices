package com.zegobird.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.product.dto.ProductTypeDto;
import com.zegobird.product.dto.SpecificationDto;
import com.zegobird.product.dto.SpecificationMainDto;
import com.zegobird.product.entity.Producttypespec;
import com.zegobird.product.entity.Specification;
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
@Api(value = "规格项信息", description = "规格项信息操作相关接口")
@RequestMapping(value="/specification",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpecificationController extends BasePageController {
    @Autowired
    private ISpecificationService iSpecificationService;

    @Autowired
    private ISpecificationvalueService iSpecificationvalueService;

    @Autowired
    private IProducttypeService iProducttypeService;

    @Autowired
    private IProducttypespecService iProducttypespecService;

    @Autowired
    private IProducttypespecvalueService iProducttypespecvalueService;

    @Autowired
    private IProductinfospecrelationService iProductinfospecrelationService;

    @Autowired
    private IProductattributeinfoService iProductattributeinfoService;

    @GetMapping("/list")
    @ApiModelProperty("规格项信息列表")
    public String getSpecificationList(Boolean issaleprop){
        List<Specification> specificationList= iSpecificationService.getSpeclistBySale(issaleprop);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("res",specificationList);
        return formatResponseParams(EXEC_OK,jsonObject);
    }

    @PostMapping("/add")
    @ApiOperation("添加规格项信息")
    @Transactional
    public String addSpecification(@RequestBody @Validated({SpecificationMainDto.Create.class}) SpecificationMainDto specificationMainDto){
        SpecificationDto specificationDto = specificationMainDto.getSpecificationDto();
        Specification specification = specificationDto.convert(Specification.class);
        boolean s = iSpecificationService.save(specification);
        if(s){
        //Integer[] typeids = specificationMainDto.getTypeids();
        //List<Producttype> list= iProducttypeService.getTypeListbyIds(typeids);
            List<ProductTypeDto>  productTypeDtos = specificationMainDto.getProductTypeDtos();
                if(productTypeDtos.size()>0) {
                    for (int i = 0; i < productTypeDtos.size(); i++) {
                        ProductTypeDto productTypeDto=productTypeDtos.get(i);
                        Producttypespec producttypespec = new Producttypespec();
                        producttypespec.setSpecificationid(specification.getSpecificationid());
                        producttypespec.setSpecname(specificationDto.getSpecificationname());
                        producttypespec.setDisplaymode(specificationDto.getDisplaymode());
                        producttypespec.setIsmust(specificationDto.getIsmust());
                        producttypespec.setIssaleprop(specificationDto.getIssaleprop());
                        producttypespec.setIscolorprop(specificationDto.getIscolorprop());
                        producttypespec.setOrderno(specificationDto.getOrderno());
                        producttypespec.setTypeid(productTypeDto.getId());
                        producttypespec.setTypename(productTypeDto.getTypename());
                        producttypespec.setFulltypeid(productTypeDto.getFulltypeid());
                        producttypespec.setFulltypename(productTypeDto.getFulltypename());
                        iProducttypespecService.save(producttypespec);
                    }
                }
        }
        return formatResponseParams(EXEC_OK, null);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "更新规格项信息")
    @Transactional
    public String update(@PathVariable("id") Integer id, @RequestBody @Validated({SpecificationMainDto.Update.class})
            SpecificationMainDto specificationMainDto){
        SpecificationDto specificationDto = specificationMainDto.getSpecificationDto();
        Specification specification = specificationDto.convert(Specification.class);
        specification.setSpecificationid(id);
        boolean s = iSpecificationService.updateById(specification);
        if(s){
            specification = iSpecificationService.getById(id);
            List<ProductTypeDto>  productTypeDtos = specificationMainDto.getProductTypeDtos();
            if(productTypeDtos.size()>0) {
                for (int i = 0; i < productTypeDtos.size(); i++) {
                    ProductTypeDto productTypeDto = productTypeDtos.get(i);
                    if (productTypeDto.getAction() == 0) {
                        Producttypespec producttypespec = new Producttypespec();
                        producttypespec.setSpecificationid(specification.getSpecificationid());
                        producttypespec.setSpecname(specification.getSpecificationname());
                        producttypespec.setDisplaymode(specification.getDisplaymode());
                        producttypespec.setIsmust(specification.getIsmust());
                        producttypespec.setIssaleprop(specification.getIssaleprop());
                        producttypespec.setIscolorprop(specification.getIscolorprop());
                        producttypespec.setOrderno(specification.getOrderno());
                        producttypespec.setTypeid(productTypeDto.getId());
                        producttypespec.setTypename(productTypeDto.getTypename());
                        producttypespec.setFulltypeid(productTypeDto.getFulltypeid());
                        producttypespec.setFulltypename(productTypeDto.getFulltypename());
                        iProducttypespecService.save(producttypespec);
                    } else {
                        Producttypespec producttypespec = iProducttypespecService.getProducttypespec(specification.getSpecificationid(), productTypeDto.getId());//获取类目规格项ID
                        if (producttypespec != null) {
                            if (productTypeDto.getAction() == 1) {
                                producttypespec.setSpecname(specification.getSpecificationname());
                                producttypespec.setDisplaymode(specification.getDisplaymode());
                                producttypespec.setIsmust(specification.getIsmust());
                                producttypespec.setIssaleprop(specification.getIssaleprop());
                                producttypespec.setIscolorprop(specification.getIscolorprop());
                                producttypespec.setOrderno(specification.getOrderno());
                                producttypespec.setTypeid(productTypeDto.getId());
                                producttypespec.setTypename(productTypeDto.getTypename());
                                producttypespec.setFulltypeid(productTypeDto.getFulltypeid());
                                producttypespec.setFulltypename(productTypeDto.getFulltypename());
                                iProducttypespecService.updateById(producttypespec);
                            } else if (productTypeDto.getAction() == 2) {
                                iProducttypespecService.removeById(producttypespec.getTypespecid());
                            }
                        }
                    }
                }
            }
        }
        return formatResponseParams(EXEC_OK, null);
    }

    @GetMapping("/delete/{id}")
    @ApiModelProperty("删除规格项信息")
    @Transactional
    public String delSpec(@PathVariable("id") Integer id){
        if(iProductinfospecrelationService.getProdSpecCountBySpecid(id)>0||iProductattributeinfoService.getProdAttriCountBySpecid(id)>0){
            return formatResponseParams(EXEC_ERROR, null);
        }else {
            iProductinfospecrelationService.getProdSpecCountBySpecid(id);
            Integer a1 = iProducttypespecvalueService.delBySpecid(id);////删除与规格项对应的类别规格值
            Integer a2 = iSpecificationvalueService.delBySpecid(id);//删除与规格项对应的规格值
            Integer b1 = iProducttypespecService.delBySpecid(id);//删除与规格项对应的类别规格项
            boolean b = iSpecificationService.removeById(id);//删除规格项
            return formatResponseParams(EXEC_OK, null);
        }
    }
}

