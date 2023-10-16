package com.devmare.blog_app_apis.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Blog API")
                                .description("This is Blog API description")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Ankush Paul")
                                                .email("anksuhpaul199@gmail.com")
                                                .url("https://github.com/Ankush-Paul-02")
                                )
                                .license(new License().name("Devmare"))
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .url("https://github.com/Ankush-Paul-02")
                                .description("This is external documentation")
                );
    }
}
