package com.zegobird.product.service;

import com.zegobird.product.dto.ProAttrInfoDto;
import com.zegobird.product.entity.Productattributeinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性表 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
public interface IProductattributeinfoService extends IService<Productattributeinfo> {
    List<ProAttrInfoDto> getProAttributeList(Integer productid);
    Integer getProdAttriCountBySpecid (Integer specificationid);
}
