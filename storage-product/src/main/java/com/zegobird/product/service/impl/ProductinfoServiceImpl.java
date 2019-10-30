package com.zegobird.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zegobird.product.dto.ProductMainDto;
import com.zegobird.product.dto.ProductQuery;
import com.zegobird.product.dto.ProductInfoSkuDto;
import com.zegobird.product.dto.ProductwithSkus;
import com.zegobird.product.entity.Productinfo;
import com.zegobird.product.mapper.ProductinfoMapper;
import com.zegobird.product.service.IProductinfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品信息 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-05
 */
@Service
public class ProductinfoServiceImpl extends ServiceImpl<ProductinfoMapper, Productinfo> implements IProductinfoService {
    @Override
    public IPage<ProductInfoSkuDto> getProductSkuList(IPage<ProductInfoSkuDto> page, ProductQuery query) {
        List<ProductInfoSkuDto> productSkuList = baseMapper.getProductSkuListResultMap(query);
        page.setRecords(productSkuList);
        return page;
    }

    @Override
    public ProductwithSkus getProductSkuDetail(Integer id) {
        return baseMapper.getProductSkuDetail(id);
    }

    @Override
    public ProductwithSkus getProductSkuDetailBySku(String sku) {
        return baseMapper.getProductSkuDetailBySku(sku);
    }

    @Override
    public String getMaxProductCode(String spuPrefix) {
        return  baseMapper.getMaxProductCode(spuPrefix);
    }
}
