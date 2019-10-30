package com.zegobird.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.product.dto.ProAttrInfoDto;
import com.zegobird.product.entity.Productattributeinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品属性表 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ProductattributeinfoMapper extends BaseMapper<Productattributeinfo> {
    Integer getProdAttriCountBySpecid (@Param("specificationid") Integer specificationid);

}
