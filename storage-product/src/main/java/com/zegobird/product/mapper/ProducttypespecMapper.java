package com.zegobird.product.mapper;

import com.zegobird.product.dto.ProducttypespecwithValueDto;
import com.zegobird.product.entity.Producttypespec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zegobird.product.entity.Specification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 类目规格属性 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ProducttypespecMapper extends BaseMapper<Producttypespec> {
    Producttypespec getProducttypespec(@Param("specificationid")Integer specificationid, @Param("typeid")Integer typeid);
    List<ProducttypespecwithValueDto> getProtypespecwithValue(@Param("typeid")Long typeid, @Param("issaleprop")Boolean issaleprop);
    Integer delBySpecid(Integer specificationid);
}
