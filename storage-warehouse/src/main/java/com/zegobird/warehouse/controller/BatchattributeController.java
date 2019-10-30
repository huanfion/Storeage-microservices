package com.zegobird.warehouse.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zegobird.common.enums.ErrorCodeEnum;
import com.zegobird.common.framework.BasePageController;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.common.model.ErrorCode;
import com.zegobird.common.util.ApiAssert;
import com.zegobird.warehouse.entity.Batchattribute;
import com.zegobird.warehouse.entity.Batchattributerule;
import com.zegobird.warehouse.entity.Batchattributetotype;
import com.zegobird.warehouse.entity.vo.BatchAttrInput;
import com.zegobird.warehouse.entity.vo.BatchAttrOutput;
import com.zegobird.warehouse.entity.vo.BatchAttrRuleInput;
import com.zegobird.warehouse.entity.vo.productTypeDto;
import com.zegobird.warehouse.service.IBatchattributeService;
import com.zegobird.warehouse.service.IBatchattributeruleService;
import com.zegobird.warehouse.service.IBatchattributetotypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 批次属性 前端控制器
 * </p>
 *
 * @author huanfion
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/batchattr")
@Api(value = "批次属性相关接口")
public class BatchattributeController extends BasePageController {
    @Autowired
    private IBatchattributeService batchatrrService;
    @Autowired
    private IBatchattributeruleService attrruleService;

    @Autowired
    private IBatchattributetotypeService attrtotypeService;

    @GetMapping("/page")
    @ApiOperation("批次属性规则分页列表")
    public String getBatchAttrRule(@RequestParam(required = false,value = "name") String name){
        IPage<Batchattribute> batchattributerulePageList=batchatrrService.page(getPage(),
                new QueryWrapper<Batchattribute>().like("batchattribute",name).orderByDesc("createdate"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", batchattributerulePageList);
        return formatResponseParams(EXEC_OK, jsonObject);
    }
    @PostMapping("/add")
    @ApiOperation("新增批次属性规则")
    @Transactional
    public String createBatchAttrRule(@Validated @RequestBody BatchAttrInput input){
        // region ====1.新增批次属性表=====
        Batchattribute batchattribute = input.convert(Batchattribute.class);
        QueryWrapper<Batchattribute> wrapper = new QueryWrapper<Batchattribute>()
                .eq("batchattribute", batchattribute.getBatchattribute());
        Batchattribute one = batchatrrService.getOne(wrapper);
        ApiAssert.isNull(new ErrorCode("已存在相同名称的批次属性规则",500,true,"已存在相同名称的批次属性规则"),one);
        batchatrrService.save(batchattribute);
        // endregion
        // region ====2.新增批次属性规则数据====
        List<Batchattributerule> batchattributerules = BeanConverter.convert(Batchattributerule.class, input.getBatchatrrrulelist());
        batchattributerules.forEach(e->e.setBatchattributeid(batchattribute.getBatchattributeid()));
        attrruleService.saveBatch(batchattributerules);
        //endregion
        //region ====3.批次属性关联表新增数据====
        List<Batchattributetotype> batchattributetotypes = BeanConverter.convert(Batchattributetotype.class, input.getProducttypelist());
        batchattributetotypes.forEach(e->e.setBatchattributeid(batchattribute.getBatchattributeid()));
        attrtotypeService.saveBatch(batchattributetotypes);
        //endregion
        JSONObject jsonObject = new JSONObject();
        return formatResponseParams(EXEC_OK, null);
    }

    @PostMapping("/update")
    @ApiOperation("更新批次属性规则")
    @Transactional
    public String updateBatchAttrRule(@Validated @RequestBody BatchAttrInput input){
        Batchattribute byId = batchatrrService.getById(input.getBatchattributeid());
        ApiAssert.notNull(ErrorCodeEnum.ID_QUERY_DATA_NOT_FOUND, byId);//验证当前id批次属性是否存在
        Batchattribute batchattribute = input.convert(Batchattribute.class);
        batchatrrService.updateById(batchattribute);//更新批次属性

        //1.删除批次属性关联表
        attrtotypeService.remove(new QueryWrapper<Batchattributetotype>().eq("batchattributeid",input.getBatchattributeid()));
        //2.删除批次属性规则表
        attrruleService.remove(new QueryWrapper<Batchattributerule>().eq("batchattributeid",input.getBatchattributeid()));
        //region ====3.批次属性关联表新增数据====
        List<Batchattributetotype> batchattributetotypes = BeanConverter.convert(Batchattributetotype.class, input.getProducttypelist());
        batchattributetotypes.forEach(e->e.setBatchattributeid(batchattribute.getBatchattributeid()));
        attrtotypeService.saveBatch(batchattributetotypes);
        //endregion
        // region ====4.新增批次属性规则表数据====
        List<Batchattributerule> batchattributerules = BeanConverter.convert(Batchattributerule.class, input.getBatchatrrrulelist());
        batchattributerules.forEach(e->e.setBatchattributeid(batchattribute.getBatchattributeid()));
        attrruleService.saveBatch(batchattributerules);

        JSONObject jsonObject = new JSONObject();
        return formatResponseParams(EXEC_OK, null);
    }

    @ApiOperation("更新批次属性状态")
    @PutMapping("/{id}/state")
    public String updateStatus(@PathVariable("id") Long id, @Valid @RequestParam Integer state) {
        Batchattribute batchattribute = batchatrrService.getById(id);
        ApiAssert.notNull(ErrorCodeEnum.ID_QUERY_DATA_NOT_FOUND, batchattribute);
        batchattribute.setState(state);
        batchatrrService.updateById(batchattribute);
        return formatResponseParams(EXEC_OK, null);
    }
    @ApiOperation("根据产品类型获取批次属性规格列表")
    @GetMapping("/{typeid}/attrrules")
    public String getBatchattrrulesByProductType(@PathVariable("typeid") Long typeid){
        BatchAttrOutput batchAttrOutput = batchatrrService.selectBatchAttrWithRules(typeid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res",batchAttrOutput);
        return formatResponseParams(EXEC_OK, jsonObject);
    }
}

