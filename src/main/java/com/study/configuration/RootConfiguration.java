package com.study.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.study.service", "com.study.repository"})
@Import(JdbcConfig.class)
public class RootConfiguration {
}
