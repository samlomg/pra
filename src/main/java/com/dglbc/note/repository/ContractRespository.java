package com.dglbc.note.repository;

import com.dglbc.note.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContractRespository extends JpaRepository<Contract,Long>,JpaSpecificationExecutor<Contract> {
}
