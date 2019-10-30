package com.zegobird.product.service;

import com.zegobird.product.dto.ProducttypespecwithValueDto;
import com.zegobird.product.entity.Producttypespec;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 类目规格属性 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface IProducttypespecService extends IService<Producttypespec> {
     Producttypespec getProducttypespec(Integer specificationid, Integer typeid);

     /**
      * 获取产品类目规格项及值
      * @param typeid
      * @param issaleprop 是否销售属性，true 获取产品类目规格；false 获取产品类型属性
      * @return
      */
     List<ProducttypespecwithValueDto> getProtypespecwithValue(Long typeid,Boolean issaleprop);

     Integer delBySpecid(Integer specificationid);
}
