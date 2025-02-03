package com.example.StartProject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Start Project API")
                        .version("1.0.0")
                        .description("ApI 문서"));
    }
}
