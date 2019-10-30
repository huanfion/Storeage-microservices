package com.zegobird.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.product.entity.Specificationvalue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 规格属性值表 服务类
 * </p>
 * @author huanfion
 * @since 2019-09-04
 */
public interface ISpecificationvalueService extends IService<Specificationvalue> {
    IPage<Specificationvalue> getSpecificationvaluePageList(IPage<Specificationvalue> page,Integer specificationid);
    IPage<Specificationvalue> searchSpecificationvalue(IPage<Specificationvalue> page,String specvalue,int state);
    Integer delBySpecid(Integer specificationid);
}
