package com.zegobird.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zegobird.warehouse.entity.Batchattribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zegobird.warehouse.entity.vo.BatchAttrOutput;

/**
 * <p>
 * 批次属性 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-10-29
 */
public interface IBatchattributeService extends IService<Batchattribute> {


    BatchAttrOutput selectBatchAttrWithRules(Long typeid);
}
