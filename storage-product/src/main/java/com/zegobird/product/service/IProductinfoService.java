package com.zegobird.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.product.dto.ProductMainDto;
import com.zegobird.product.dto.ProductQuery;
import com.zegobird.product.dto.ProductInfoSkuDto;
import com.zegobird.product.dto.ProductwithSkus;
import com.zegobird.product.entity.Productinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品信息 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-05
 */
public interface IProductinfoService extends IService<Productinfo> {
    IPage<ProductInfoSkuDto> getProductSkuList(IPage<ProductInfoSkuDto> page, ProductQuery query);

    ProductwithSkus getProductSkuDetail(Integer id);

    /**
     * 获取当前前缀的最大值
     * @param spuPrefix
     * @return
     */
    String getMaxProductCode(String spuPrefix);

    ProductwithSkus getProductSkuDetailBySku(String sku);
}
