package com.zegobird.warehouse.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zegobird.warehouse.entity.Batchattribute;
import com.zegobird.warehouse.entity.vo.BatchAttrOutput;
import com.zegobird.warehouse.mapper.BatchattributeMapper;
import com.zegobird.warehouse.service.IBatchattributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 批次属性 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-10-29
 */
@Service
public class BatchattributeServiceImpl extends ServiceImpl<BatchattributeMapper, Batchattribute> implements IBatchattributeService {

    @Autowired
    private BatchattributeMapper batchattributeMapper;

    @Override
    public BatchAttrOutput selectBatchAttrWithRules(Long typeid) {
        return batchattributeMapper.selectBatchAttrWithRules(typeid);
    }
}
