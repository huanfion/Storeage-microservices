package com.zegobird.product.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.product.entity.Specificationvalue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规格属性值表 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface SpecificationvalueMapper extends BaseMapper<Specificationvalue> {
    List<Specificationvalue> getSpecificationvaluePageList(IPage<Specificationvalue> page,@Param("specificationid") Integer specificationid);
    List<Specificationvalue> searchSpecificationvalue(IPage<Specificationvalue> page,@Param("specvalue") String specvalue,@Param("state") String state);
    Integer delBySpecid(@Param("specificationid")Integer specificationid);
}
