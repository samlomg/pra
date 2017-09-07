package com.dglbc.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by LbcLT on 2016/12/11.
 */
@Configurable
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class SpringTransactionManagement {
}
