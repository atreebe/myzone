package com.example.myzone.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.example.myzone.interceptor.AdminLoginInterceptor;

@SpringBootConfiguration
public class AdminLoginAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/**.html","/admin/**","/add**");
        //registry.excludePathPatterns("/login/**");
        super.addInterceptors(registry);
    }
}