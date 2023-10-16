package com.devmare.blog_app_apis.configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "scheme1",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Blog Application APIs",
                description = "Application description",
                version = "v1.0.0",
                contact = @Contact(
                        name = "Ankush Paul",
                        email = "anksuhpaul199@gmail.com",
                        url = "https://github.com/Ankush-Paul-02"
                ),
                license = @License(
                        name = "Devmare"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "This is external documentation",
                url = "https://github.com/Ankush-Paul-02"
        )
)
public class SwaggerConfiguration {

    /*@Bean
    public OpenAPI openAPI() {
        String schemeName = "bearerScheme";
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(schemeName)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(schemeName, new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat("JWT")
                                        .scheme("bearer")
                                )
                )
                .info(
                        new Info()
                                .title("Blog Application APIs")
                                .description("Application description")
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
    }*/
}
