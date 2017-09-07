package com.dglbc.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by LbcLT on 2016/12/10.
 */

@EnableWebMvc
@ComponentScan("com.dglbc")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login/form").setViewName("login");
        registry.addViewController("welcome").setViewName("welcome");
        registry.addViewController("home").setViewName("home");
        registry.addViewController("admin").setViewName("admin");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/resources/").setCachePeriod(31556926);
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver resolver(){
        InternalResourceViewResolver url = new InternalResourceViewResolver();
        url.setPrefix("/WEB-INF/jsp/");
        url.setSuffix(".jsp");
        return url;
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.dao.DataAccessException", "error");
        b.setExceptionMappings(mappings);
        return b;
    }

}
