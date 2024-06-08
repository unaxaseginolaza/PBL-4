package com.example.pbl4.config;

import com.example.pbl4.filter.RoleBasedEmployeeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RoleBasedEmployeeFilter> loggingFilter() {
        FilterRegistrationBean<RoleBasedEmployeeFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RoleBasedEmployeeFilter());
        registrationBean.addUrlPatterns("/employee/*", "/employee/*/*");

        return registrationBean;
    }
}