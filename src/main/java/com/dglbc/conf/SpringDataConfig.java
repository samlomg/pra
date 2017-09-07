package com.dglbc.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by LbcLT on 2016/12/11.
 */

@Configuration
@EnableJpaRepositories(basePackages= {"com.dglbc.login.repository","com.dglbc.worklists.repository","com.dglbc.note.repository"})
public class SpringDataConfig {
}
