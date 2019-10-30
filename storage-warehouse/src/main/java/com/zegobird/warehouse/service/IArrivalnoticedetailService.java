package com.zegobird.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zegobird.warehouse.entity.Arrivalnoticedetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zegobird.warehouse.entity.vo.ArrivalnoticedetailInput;

import java.util.List;

/**
 * <p>
 * 到货通知单明细表 服务类
 * </p>
 *
 * @author huanfion
 * @since 2019-10-19
 */
public interface IArrivalnoticedetailService extends IService<Arrivalnoticedetail> {

    boolean UpdateArrivalNoticeDetailList(List<ArrivalnoticedetailInput> arrivalnoticedetailList);

    List<Arrivalnoticedetail> SelectArrDetailList(Long arrivalnoticeid);
}
