package com.epam.demo.managerassignment.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName ("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/myDb")
                .username("springuser")
                .password("Ars-Pass1234")
                .build();
    }
}
