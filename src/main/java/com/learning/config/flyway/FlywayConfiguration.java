package com.learning.config.flyway;

import com.learning.service.TenantDomain;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class FlywayConfiguration {

    @Value("${flyway.url}")
    private String flywayUrl;
    @Value("${flyway.user}")
    private String flywayUser;
    @Value("${flyway.password}")
    private String flywayPassword;
    @Value("${spring.datasource.driverClassName}")
    private String flywayDriver;

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(flywayDriver)
                .url(flywayUrl)
                .username(flywayUser)
                .password(flywayPassword)
                .build();
    }

    @Bean
    FlywayBuilder flywayBuilder() {
        return new FlywayBuilder(dataSource());
    }

    @Bean
    Flyway flyway() {
        Flyway flyway = flywayBuilder().createDefaultFlyway();
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner commandLineRunner(TenantDomain tenantDomain) {
        return args -> tenantDomain.getAllTenants().forEach(tenant -> {
            Flyway flyway = flywayBuilder().createTenantFlyway(tenant);
            flyway.migrate();
        });
    }

}
