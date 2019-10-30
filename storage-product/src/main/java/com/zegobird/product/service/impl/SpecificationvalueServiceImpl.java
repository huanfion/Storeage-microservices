package com.zegobird.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.product.entity.Specificationvalue;
import com.zegobird.product.mapper.SpecificationvalueMapper;
import com.zegobird.product.service.ISpecificationvalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 规格属性值表 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class SpecificationvalueServiceImpl extends ServiceImpl<SpecificationvalueMapper, Specificationvalue> implements ISpecificationvalueService {
    @Override
    public IPage<Specificationvalue> getSpecificationvaluePageList(IPage<Specificationvalue> page,Integer specificationid) {
        List<Specificationvalue> list= baseMapper.getSpecificationvaluePageList(page,specificationid);
        page.setRecords(list);
        return page;
    }

    @Override
    public IPage<Specificationvalue> searchSpecificationvalue(IPage<Specificationvalue> page,String specvalue,int state){
        //List<Specificationvalue> list= baseMapper.searchSpecificationvalue(page,specvalue,state);
        //page.setRecords(list);
        return page;
    }

    @Override
    public Integer delBySpecid(Integer specificationid){
        return baseMapper.delBySpecid(specificationid);
    }
}
