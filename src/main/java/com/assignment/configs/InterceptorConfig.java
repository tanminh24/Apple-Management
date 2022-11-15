package com.assignment.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.assignment.interceptors.GuestInterceptor;
import com.assignment.interceptors.UserInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  
    @Autowired
    private GuestInterceptor guestInterceptor;

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(guestInterceptor)
                .addPathPatterns("/admin/**", "/cart/**")
                .excludePathPatterns("account/login", "account/register", "account/logout");

        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/admin/**", "/account/login", "account/register")
                .excludePathPatterns("account/logout");
        
    }

}
