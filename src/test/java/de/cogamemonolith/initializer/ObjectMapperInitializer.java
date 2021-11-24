package de.cogamemonolith.initializer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@TestConfiguration
public class ObjectMapperInitializer {


    @Bean
    ObjectMapper groups() throws IOException {

        return new ObjectMapper();

    }


}
