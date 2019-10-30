package com.zegobird.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.product.dto.ProductSpecvalueDto;
import com.zegobird.product.dto.ProductSpecwithValueDto;
import com.zegobird.product.entity.Productinfospecrelation;
import com.zegobird.product.entity.Productinfospecvaluerelatio;
import com.zegobird.product.mapper.ProductinfospecrelationMapper;
import com.zegobird.product.mapper.ProductinfospecvaluerelatioMapper;
import com.zegobird.product.service.IProductinfospecrelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 产品规格项 服务实现类
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Service
public class ProductinfospecrelationServiceImpl extends ServiceImpl<ProductinfospecrelationMapper, Productinfospecrelation> implements IProductinfospecrelationService {
    @Autowired
    private ProductinfospecvaluerelatioMapper productinfospecvaluerelatioMapper;

    @Override
    public List<ProductSpecwithValueDto> getProductSpecWithValue(Long productid) {
        return baseMapper.getProductSpecWithValue(productid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveProductSpecWithValue(List<ProductSpecwithValueDto> list) {

        try (SqlSession sqlSession = sqlSessionBatch()) {
            list.forEach(e -> {
                insertProductSpecWithValue(e);
            });
        }
        return true;
    }

    public Boolean insertProductSpecWithValue(ProductSpecwithValueDto dto) {
        Productinfospecrelation productinfospecrelation = dto.convert(Productinfospecrelation.class);
        baseMapper.insert(productinfospecrelation);
        List<ProductSpecvalueDto> productspecvalueList = dto.getProductspecvalueList();
        productspecvalueList.forEach(v -> {
            Productinfospecvaluerelatio productinfospecvaluerelatio = v.convert(Productinfospecvaluerelatio.class);
            productinfospecvaluerelatio.setProductspecid(productinfospecrelation.getProductspecid());
            productinfospecvaluerelatioMapper.insert(productinfospecvaluerelatio);
        });
        return true;
    }

    public Boolean deleteProductSpecWithValue(ProductSpecwithValueDto dto) {
        Productinfospecrelation productinfospecrelation = dto.convert(Productinfospecrelation.class);
        //先删除外键表 产品规格值表
        productinfospecvaluerelatioMapper.delete(
                new QueryWrapper<Productinfospecvaluerelatio>().
                        eq("productspecid", productinfospecrelation.getProductspecid()));
        //再删除主键表 产品规格项表
        baseMapper.deleteById(productinfospecrelation.getProductspecid());
        return true;
    }

    public Boolean updateProductSpecWithValue(ProductSpecwithValueDto dto) {
        //删除产品规格值，重新插入
        Productinfospecrelation productinfospecrelation = dto.convert(Productinfospecrelation.class);
        productinfospecvaluerelatioMapper.delete(
                new QueryWrapper<Productinfospecvaluerelatio>().
                        eq("productspecid", productinfospecrelation.getProductspecid()));
        List<ProductSpecvalueDto> productspecvalueList = dto.getProductspecvalueList();
        productspecvalueList.forEach(v -> {
            Productinfospecvaluerelatio productinfospecvaluerelatio = v.convert(Productinfospecvaluerelatio.class);
            productinfospecvaluerelatio.setProductspecid(productinfospecrelation.getProductspecid());
            productinfospecvaluerelatioMapper.insert(productinfospecvaluerelatio);
        });
        return true;
    }

    /**
     * 更新产品规格项和规格值
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProductSpeList(List<ProductSpecwithValueDto> list) {
        list.forEach(dto -> {
            if (dto.getAction() == "add") {
                insertProductSpecWithValue(dto);
            } else if (dto.getAction() == "delete") {
                deleteProductSpecWithValue(dto);
            } else if (dto.getAction() == "update") {
                updateProductSpecWithValue(dto);
            }
        });
        return true;
    }

    @Override
    public Integer getProdSpecCountBySpecid(Integer specificationid){
        return baseMapper.getProdSpecCountBySpecid(specificationid);
    }
}
