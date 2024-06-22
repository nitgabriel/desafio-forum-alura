package br.com.alura.desafioforumalura.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Fórum ALURA")
                        .description("O Fórum Alura é uma  API REST desenvolvida em Java utilizando Spring Boot. \n" +
                                "Nesta API, os usuários podem criar, \n" +
                                "visualizar, atualizar e deletar tópicos de discussão, respostas e outras ações.")
                        .contact(new Contact()
                                .name("Time ALURA")
                                .email("forum@alura.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forum.hub/api/licenca")));
    }
}
