package com.zegobird.product.mapper;

import com.zegobird.product.entity.Specification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规格属性表 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface SpecificationMapper extends BaseMapper<Specification> {
    List<Specification> getSpeclistBySale(@Param("issaleprop") Boolean issaleprop);
}
