package com.zegobird.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zegobird.warehouse.entity.Arrivalnotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zegobird.warehouse.entity.vo.ArrivalNoticeWithDetaiListOutput;
import com.zegobird.warehouse.entity.vo.ArrivalNoticeParam;
import com.zegobird.warehouse.entity.vo.ArrivalNoticeWithDetailOutput;

/**
 * <p>
 * 到货通知单 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-10-19
 */
public interface IArrivalnoticeService extends IService<Arrivalnotice> {

    IPage<ArrivalNoticeWithDetaiListOutput> selectArrivalNoticePageList(Page<ArrivalNoticeWithDetaiListOutput> page, ArrivalNoticeParam param);

    ArrivalNoticeWithDetaiListOutput selectArrivalNoticeWithDetailListById(Long id);

    ArrivalNoticeWithDetailOutput selectArrivalNoticeWithDetailById(Long id);
}
