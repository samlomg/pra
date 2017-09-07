package com.dglbc.note.repository;

import com.dglbc.note.entity.SalesPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by LbcLT on 2017/5/17.
 */
@Repository
public interface SalesPlanRepository extends JpaRepository<SalesPlan,Long>,JpaSpecificationExecutor<SalesPlan> {

    List<SalesPlan> findByFlagAndCreateTimeBetween(int flag, Date startTime, Date endTime);

    SalesPlan findBySalesPlanId(long salesInvoceId);
}
