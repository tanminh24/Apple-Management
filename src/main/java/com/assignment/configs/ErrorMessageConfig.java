package com.assignment.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ErrorMessageConfig {

    @Bean(name = "messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:messages/category");
        ms.addBasenames("classpath:messages/register");
        ms.addBasenames("classpath:messages/product");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }

}
