package de.cogamemonolith.initializer;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Class to inherit from other database tester classes
 * Sets postgres container
 */

public class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
                .withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("database");
        container.start();

        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
        System.setProperty("spring.liquibase.url", container.getJdbcUrl());
        System.setProperty("spring.liquibase.user", container.getUsername());
        System.setProperty("spring.liquibase.password", container.getPassword());
        System.setProperty("spring.liquibase.enabled", "true");
    }

}





