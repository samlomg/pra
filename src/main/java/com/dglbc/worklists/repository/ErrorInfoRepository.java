package com.dglbc.worklists.repository;

import com.dglbc.worklists.entity.ErrorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by LbcLT on 2017/2/26.
 */
public interface ErrorInfoRepository  extends JpaRepository<ErrorInfo,Long>,JpaSpecificationExecutor<ErrorInfo> {
}
