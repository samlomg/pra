package com.dglbc.note.repository;

import com.dglbc.note.entity.CostTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CostTypesRespository extends JpaRepository<CostTypes,Long>,JpaSpecificationExecutor<CostTypes> {
}
