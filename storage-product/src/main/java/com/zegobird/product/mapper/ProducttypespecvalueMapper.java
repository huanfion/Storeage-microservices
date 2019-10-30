package com.zegobird.product.mapper;

import com.zegobird.product.entity.Producttypespecvalue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 类目规格属性值 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ProducttypespecvalueMapper extends BaseMapper<Producttypespecvalue> {
    Integer delBySpecid(Integer specificationid);
}
