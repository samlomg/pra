package com.dglbc.worklists.service.impl;

import com.dglbc.worklists.entity.ErrorInfo;
import com.dglbc.worklists.repository.ErrorInfoRepository;
import com.dglbc.worklists.service.ErrorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LbcLT on 2017/3/1.
 */
@Service("errorInfoService")
@Transactional
public class ErrorInfoServiceImpl implements ErrorInfoService {
    @Autowired
    ErrorInfoRepository errorInfoRepository;

    @Override
    public Boolean save(ErrorInfo errorInfo) {
        errorInfoRepository.save(errorInfo);
        return true;
    }
}
