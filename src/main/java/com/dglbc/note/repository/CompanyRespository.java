package com.dglbc.note.repository;

import com.dglbc.note.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyRespository extends JpaRepository<Company,Long>,JpaSpecificationExecutor<Company> {
}
