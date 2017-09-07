package com.dglbc.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by LbcLT on 2016/12/10.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SecurityEvaluationContextExtension expressionEvaluationContextProvider() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("c3p0.initialPoolSize")));
        dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("c3p0.acquireIncrement")));
        dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("c3p0.minPoolSize")));
        dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("c3p0.maxPoolSize")));
        dataSource.setMaxIdleTime(Integer.valueOf(env.getProperty("c3p0.maxIdleTime")));
        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(env.getProperty("c3p0.idleConnectionTestPeriod")));
        dataSource.setAcquireRetryAttempts(Integer.valueOf(env.getProperty("c3p0.acquireRetryAttempts")));
        dataSource.setBreakAfterAcquireFailure(Boolean.parseBoolean(env.getProperty("c3p0.breakAfterAcquireFailure")));
        dataSource.setTestConnectionOnCheckout(Boolean.parseBoolean(env.getProperty("c3p0.testConnectionOnCheckout")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.dglbc.login.entity","com.dglbc.worklists.entity","com.dglbc.note.entity");
        factory.setDataSource(dataSource());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.format_sql",env.getProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.generate_statistics",env.getProperty("hibernate.generate_statistics"));
        jpaProperties.put("hibernate.jdbc.batch_size",env.getProperty("hibernate.jdbc.batch_size"));
        jpaProperties.put("hibernate.jdbc.fetch_size",env.getProperty("hibernate.jdbc.fetch_size"));
        jpaProperties.put("hibernate.enable_lazy_load_no_trans",env.getProperty("hibernate.enable_lazy_load_no_trans"));

        factory.setJpaProperties(jpaProperties);

        factory.afterPropertiesSet();
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(env.getProperty("smtp.host"));
        mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
        mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
        mailSenderImpl.setUsername(env.getProperty("smtp.username"));
        mailSenderImpl.setPassword(env.getProperty("smtp.password"));

        Properties javaMailProps = new Properties();
        javaMailProps.put("mail.smtp.auth", true);
        javaMailProps.put("mail.smtp.starttls.enable", true);
        javaMailProps.put("mail.smtp.timeout", true);


        mailSenderImpl.setJavaMailProperties(javaMailProps);

        return mailSenderImpl;
    }
}
