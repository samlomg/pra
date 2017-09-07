package com.dglbc.note.service.impl;

import com.dglbc.note.entity.SalesPlan;
import com.dglbc.note.repository.SalesPlanRepository;
import com.dglbc.note.service.SalesPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LbcLT on 2017/5/17.
 */
@Service("salesPlanService")
public class SalesPlanServiceImpl implements SalesPlanService {

    @Autowired
    SalesPlanRepository salesPlanRepository;


    @Override
    public List<SalesPlan> findAll() {
        return salesPlanRepository.findAll();
    }

    @Override
    public List<SalesPlan> findAll(Sort var1) {
        return salesPlanRepository.findAll(var1);
    }

    @Override
    public List<SalesPlan> findAll(Iterable var1) {
        return salesPlanRepository.findAll(var1);
    }

    @Override
    public Page<SalesPlan> findAll(Specification var1, Pageable var2) {
        return salesPlanRepository.findAll(var1,var2);
    }


    @Override
    public <S extends SalesPlan> List<S> save(Iterable<S> var1) {
        return salesPlanRepository.save(var1);
    }

    @Override
    public <S extends SalesPlan> S save(S var1) {
        return salesPlanRepository.save(var1);
    }

    @Override
    public List<SalesPlan> findByFlagAndCreateTime(int flag, Date startTime, Date endTime) {
        return salesPlanRepository.findByFlagAndCreateTimeBetween(flag,startTime,endTime);
    }

    @Override
    public SalesPlan findBySalesPlanId(long salesPlanId) {
        return salesPlanRepository.findBySalesPlanId(salesPlanId);
    }
}
