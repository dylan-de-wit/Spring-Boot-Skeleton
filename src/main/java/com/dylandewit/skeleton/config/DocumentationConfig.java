package com.dylandewit.skeleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class DocumentationConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // Hide Spring endpoints
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Skeleton API")
                .description("A skeleton project")
                .version("1")
                .contact(new Contact("Dylan de Wit", "", ""))
                .build();
    }
}
