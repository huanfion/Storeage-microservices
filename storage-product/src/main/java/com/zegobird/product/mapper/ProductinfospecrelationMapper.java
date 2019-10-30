package com.zegobird.product.mapper;

import com.zegobird.product.dto.ProductSpecwithValueDto;
import com.zegobird.product.entity.Productinfospecrelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品规格项 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ProductinfospecrelationMapper extends BaseMapper<Productinfospecrelation> {

    List<ProductSpecwithValueDto> getProductSpecWithValue(@Param("productid") Long productid);

    Integer getProdSpecCountBySpecid(@Param("specificationid") Integer specificationid);
}
