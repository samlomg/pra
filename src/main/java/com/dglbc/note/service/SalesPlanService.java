package com.dglbc.note.service;

import com.dglbc.note.entity.SalesPlan;

import java.util.Date;
import java.util.List;

/**
 * Created by LbcLT on 2017/5/17.
 */
public interface SalesPlanService extends BaseService<SalesPlan> {

    List<SalesPlan> findByFlagAndCreateTime(int flag, Date startTime, Date endTime);

    SalesPlan findBySalesPlanId(long salesPlanId);
}
