package com.epam.demo;

import com.epam.demo.managerassignment.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.epam.demo.managerassignment")
@EnableJpaRepositories("com.epam.demo.managerassignment.repo")
@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class EpamDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run (EpamDemoApplication.class, args);
    }

}
