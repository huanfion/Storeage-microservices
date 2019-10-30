package com.zegobird.product.service;

import com.zegobird.product.entity.Specification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 规格属性表 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ISpecificationService extends IService<Specification> {
     List<Specification> getSpeclistBySale(Boolean issaleprop);
}
