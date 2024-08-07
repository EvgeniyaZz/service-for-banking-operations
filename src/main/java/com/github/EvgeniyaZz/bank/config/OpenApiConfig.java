package com.github.EvgeniyaZz.bank.config;

import com.github.EvgeniyaZz.bank.dto.SignUpRequest;
import com.github.EvgeniyaZz.bank.model.User;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    //    https://github.com/springdoc/springdoc-openapi/issues/915
    //    https://springdoc.org/faq.html#how-can-i-set-a-global-header

    @Bean
    GroupedOpenApi token() {
        return GroupedOpenApi.builder()
                .group("JWT Token")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addSecurityItem(new SecurityRequirement().addList("Authorization"))
                            .components(new Components()
                                    .addSecuritySchemes("Authorization", new SecurityScheme()
                                            .in(SecurityScheme.In.HEADER)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("basic"))
                            )
                            .info(new Info().title("JWT Token").description("""
                                    Service for banking operations
                                    <p>Тестовые креденшелы:<br>
                                       - user1 / 1234500<br>
                                       - user2 / 1230000
                                    </p>
                                    """));

                })
                .pathsToMatch("/token")
                .build();
    }

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addSecurityItem(new SecurityRequirement().addList("Authorization"))
                            .components(new Components()
                                    .addSchemas("User", ModelConverters.getInstance().readAllAsResolvedSchema(User.class).schema)
                                    .addSchemas("SignUpRequest", ModelConverters.getInstance().readAllAsResolvedSchema(SignUpRequest.class).schema)
                                    .addSecuritySchemes("Authorization", new SecurityScheme()
                                            .in(SecurityScheme.In.HEADER)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .name("JWT"))
                            )
                            .info(new Info().title("REST API").version("1.0").description("""
                                    Service for banking operations
                                    <p>Тестовые креденшелы:<br>
                                       - user1 / 1234500<br>
                                       - user2 / 1230000
                                    </p>
                                    """));
                })
                .pathsToMatch("/api/**")
                .build();
    }
}
