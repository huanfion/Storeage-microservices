package com.zegobird.product.service.impl;

import com.zegobird.product.entity.Producttypespecvalue;
import com.zegobird.product.mapper.ProducttypespecvalueMapper;
import com.zegobird.product.service.IProducttypespecvalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类目规格属性值 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class ProducttypespecvalueServiceImpl extends ServiceImpl<ProducttypespecvalueMapper, Producttypespecvalue> implements IProducttypespecvalueService {
    @Override
    public Integer delBySpecid(Integer specificationid){
        return baseMapper.delBySpecid(specificationid);
    }
}
