package de.cogamemonolith.swagger;

import org.springframework.beans.factory.annotation.Value;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration class for all modules in the project.
 * Provides general information (e.g. contact data)
 * and variables for various information (e.g. api description)  for all APIs in the project
 *
 * URLs:
 * http://localhost:8080/v2/api-docs           -API documentation
 * http://localhost:8080/swagger-ui/index.html -gui for API
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
//TODO: add authentification details
public class SwaggerConfig {



    private static final Contact DEFAULT_CONTACT = new Contact("Klassen Olga",
            "", "klassen.olga@fh-erfurt.de");

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

    /**
     * @return api information which will be displayed for each API on the /v2/api-docs url
     *
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "cogame-monolith api",
                        "api to manage backend for game application as part of the bachelor work",
                        "1.0.0",
                        "Terms of service",
                        DEFAULT_CONTACT,
                        "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList()))
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
