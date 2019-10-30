package com.zegobird.product.service;

import com.zegobird.product.dto.ProductTypeTree;
import com.zegobird.product.entity.Producttype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品类目 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface IProducttypeService extends IService<Producttype> {
    List<ProductTypeTree> getProductTypeTree();
    List<Producttype> getSubTypeList(Integer typeid);
    List<Producttype> getTypeListbyIds(Integer[] typeids);

    /**
     * 根据等级id获取当前等级的最大分级排序号
     * @param levelid
     * @return
     */
    Integer getMaxLevelOrderNo(Integer levelid);
}
