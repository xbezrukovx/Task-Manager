package com.example.TaskManager.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPIDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local environment");

        Contact contact = new Contact();
        contact.setName("Denis Bezrukov");
        contact.setEmail("ds.bezrukov@icloud.com");

        Info info = new Info()
                .title("Task Manager API")
                .version("1.0")
                .contact(contact)
                .description("API for task manager service.");
        return new OpenAPI().info(info).servers(List.of(localhostServer));
    }
}
