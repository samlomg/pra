package com.dglbc.worklists.repository;

import com.dglbc.worklists.entity.MissionRecorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by LbcLT on 2017/2/26.
 */
public interface MissionRecorderRepository extends JpaRepository<MissionRecorder,Long>,JpaSpecificationExecutor<MissionRecorder> {

}
