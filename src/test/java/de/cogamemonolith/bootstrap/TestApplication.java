package de.cogamemonolith.bootstrap;

import de.cogamemonolith.CogameMonolithApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Bootsrap class for Test purposes, that excludes DataSourceAutoConfiguration
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CogameMonolithApplication.class, args);
    }
}
