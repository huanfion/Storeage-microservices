package com.zegobird.product.mapper;

import com.zegobird.product.entity.Producttype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品类目 Mapper 接口
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface ProducttypeMapper extends BaseMapper<Producttype> {
    List<Producttype> getTypeListbyIds(@Param("ids") Integer[] typeids);

    /**
     * 获取当前分级的最大分级排序号
     * @param levelid
     * @return
     */
    Integer getMaxLevelOrderNo(Integer levelid);
}
