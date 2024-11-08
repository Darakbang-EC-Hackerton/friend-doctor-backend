package com.youngandhun.modulecommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;

@OpenAPIDefinition(
        info = @Info(
                title = "프렌즈닥터 API 명세서",
                version = "v1.0.0",
                description = "프렌즈닥터 API 명세서입니다."
        )
)
@Configuration
@Slf4j
public class SwaggerConfig {


    public static final String JWT_SECURITY_SCHEME = "JWT Token";

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme apiKey = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
            .scheme("bearer")
            .bearerFormat("JWT");

        return new OpenAPI()
            .components(new Components().addSecuritySchemes(JWT_SECURITY_SCHEME, apiKey));
    }
}