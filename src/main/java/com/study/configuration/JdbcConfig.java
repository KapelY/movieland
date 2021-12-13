package com.study.configuration;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Setter
@Slf4j
public class JdbcConfig {
    @Value("${datasource.driver" +
            ":org.postgresql.Driver" +
            "}")
    private String driver;

    @Value("${datasource.url" +
            ":jdbc:postgresql://localhost:5432/movieland" +
            "}")
    private String url;

    @Value("${datasource.username" +
            ":test" +
            "}")
    private String username;

    @Value("${datasource.password" +
            ":password" +
            "}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
