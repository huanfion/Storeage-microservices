package com.zegobird.product.service;

import com.zegobird.product.entity.Producttypespecvalue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 类目规格属性值 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface IProducttypespecvalueService extends IService<Producttypespecvalue> {
    Integer delBySpecid(Integer specificationid);
}
