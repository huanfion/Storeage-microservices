package com.zegobird.product.service;

import com.zegobird.product.dto.ProductSpecwithValueDto;
import com.zegobird.product.entity.Productinfospecrelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品规格项 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface IProductinfospecrelationService extends IService<Productinfospecrelation> {
    //查询product对应的规格项和规格值
    List<ProductSpecwithValueDto> getProductSpecWithValue(Long productid);

    boolean saveProductSpecWithValue(List<ProductSpecwithValueDto> list);

    /**
     * 更新产品规格项和值
     * @param list
     * @return
     */
    boolean updateProductSpeList(List<ProductSpecwithValueDto> list);

    Integer getProdSpecCountBySpecid(Integer specificationid);

}
