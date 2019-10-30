package com.zegobird.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.common.util.TreeUtils;
import com.zegobird.product.dto.ProductTypeTree;
import com.zegobird.product.entity.Producttype;
import com.zegobird.product.mapper.ProducttypeMapper;
import com.zegobird.product.service.IProducttypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品类目 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class ProducttypeServiceImpl extends ServiceImpl<ProducttypeMapper, Producttype> implements IProducttypeService {
    @Override
    public List<ProductTypeTree> getProductTypeTree() {
        QueryWrapper<Producttype> wrapper = new QueryWrapper<Producttype>().eq("state", 1);
        List<Producttype> regionlist = baseMapper.selectList(wrapper);
        List<ProductTypeTree> tree = BeanConverter.convert(ProductTypeTree.class, regionlist);
        return tree.stream().filter(e -> !parentIdNotNull(e.getParentId()))
                .map(e -> TreeUtils.findChildren(e, tree))
                .collect(Collectors.toList());
    }


    @Override
    public List<Producttype> getSubTypeList(Integer typeid) {
        QueryWrapper<Producttype> queryWrapper = new QueryWrapper<Producttype>().eq("parentid", typeid).eq("state", 1);
        List<Producttype> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Producttype> getTypeListbyIds(Integer[] typeids){
        List<Producttype> list = baseMapper.getTypeListbyIds(typeids);
        return list;
    }

    @Override
    public Integer getMaxLevelOrderNo(Integer levelid) {
        Integer maxLevelOrderNo = baseMapper.getMaxLevelOrderNo(levelid);
        return maxLevelOrderNo;
    }

    /**
     * 父ID不为0并且不为空
     *
     * @param parentId
     * @return
     */
    private boolean parentIdNotNull(Long parentId) {
        boolean flag = Objects.nonNull(parentId) && parentId != 0;
        return flag;
    }
}
