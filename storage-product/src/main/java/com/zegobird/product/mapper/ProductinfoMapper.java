package com.zegobird.product.mapper;

import com.zegobird.product.dto.ProductMainDto;
import com.zegobird.product.dto.ProductQuery;
import com.zegobird.product.dto.ProductInfoSkuDto;
import com.zegobird.product.dto.ProductwithSkus;
import com.zegobird.product.entity.Productinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品信息 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-05
 */
public interface ProductinfoMapper extends BaseMapper<Productinfo> {
//    List<ProductMainDto> getProductSkuListResultMap();

    List<ProductInfoSkuDto> getProductSkuListResultMap(@Param("query")ProductQuery query);
    ProductwithSkus getProductSkuDetail(@Param("id") Integer id);

    String  getMaxProductCode(@Param("spuPrefix") String spuPrefix);

    ProductwithSkus getProductSkuDetailBySku(String sku);
}
