package com.zegobird.product.service.impl;

import com.zegobird.product.entity.Specification;
import com.zegobird.product.mapper.SpecificationMapper;
import com.zegobird.product.service.ISpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格属性表 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {
    @Override
    public List<Specification> getSpeclistBySale(Boolean issaleprop){
        return baseMapper.getSpeclistBySale(issaleprop);
    }
}
