package com.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@PropertySource(value={"classpath:static/application.properties"})
//class WebConfig implements WebMvcConfigurer {
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
//        pspc.setLocation(new ClassPathResource("target/classes/application.properties"));
//        return pspc;
//    }
//}
