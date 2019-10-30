package com.zegobird.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.product.dto.ProAttrInfoDto;
import com.zegobird.product.entity.Productattributeinfo;
import com.zegobird.product.mapper.ProductattributeinfoMapper;
import com.zegobird.product.service.IProductattributeinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性表 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class ProductattributeinfoServiceImpl extends ServiceImpl<ProductattributeinfoMapper, Productattributeinfo> implements IProductattributeinfoService {

    @Autowired
    private ProductattributeinfoMapper attrmapper;
    @Override
    public List<ProAttrInfoDto> getProAttributeList(Integer productid) {
        QueryWrapper<Productattributeinfo> queryWrapper = new QueryWrapper<Productattributeinfo>().eq("productid",productid);
        List<Productattributeinfo> list = attrmapper.selectList(queryWrapper);
        List<ProAttrInfoDto> proAttrInfoDtos = BeanConverter.convert(ProAttrInfoDto.class, list);
        return proAttrInfoDtos;
    }

    @Override
    public Integer getProdAttriCountBySpecid (Integer specificationid){
        return baseMapper.getProdAttriCountBySpecid(specificationid);
    }
}
