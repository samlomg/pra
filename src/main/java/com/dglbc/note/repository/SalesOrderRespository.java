package com.dglbc.note.repository;

import com.dglbc.note.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesOrderRespository extends JpaRepository<SalesOrder,Long>,JpaSpecificationExecutor<SalesOrder> {
}
