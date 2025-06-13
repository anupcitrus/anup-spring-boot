package com.anup.shopapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shop Management API")
                        .version("1.0.0")
                        .description("A comprehensive REST API for managing shops with full CRUD operations, search functionality, and MySQL database integration.")
                        .contact(new Contact()
                                .name("Anup Anthony")
                                .email("anup.anthony+resmed@brightree.com")
                                .url("https://github.com/anupcitrus/anup-spring-boot"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local Development Server"),
                        new Server()
                                .url("http://10.10.10.124:" + serverPort)
                                .description("Network Development Server")
                ));
    }
}
