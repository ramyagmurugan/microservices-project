package com.project.apigateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        String securitySchemeName = "oauth2";

        return new OpenAPI()

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(
                                        securitySchemeName,
                                        Arrays.asList(
                                                "read",
                                                "write"
                                        )
                                )
                )

                .components(
                        new Components()

                                .addSecuritySchemes(
                                        securitySchemeName,

                                        new SecurityScheme()

                                                .type(
                                                        SecurityScheme.Type.OAUTH2
                                                )

                                                .flows(
                                                        new OAuthFlows()

                                                                .clientCredentials(

                                                                        new OAuthFlow()

                                                                                .tokenUrl(
                                                                                        "http://localhost:9000/oauth2/token"
                                                                                )

                                                                                .scopes(
                                                                                        new Scopes()

                                                                                                .addString(
                                                                                                        "read",
                                                                                                        "Read access"
                                                                                                )

                                                                                                .addString(
                                                                                                        "write",
                                                                                                        "Write access"
                                                                                                )
                                                                                )
                                                                )
                                                )
                                )
                );
    }
}
