package com.fiap.mercadoexpress.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mercado Express API — FIAP Checkpoint 4")
                        .description("API RESTful para gestão de estoque e produtos do Mercado Express com suporte a HATEOAS (Maturidade Nível 3), Spring Data JPA e Oracle DB.\n\n" +
                                "**Integrantes:**\n" +
                                "- Gabriel Maciel (RM562795)\n" +
                                "- Vitória Rodrigues Martins (RM565160)\n" +
                                "- Augusto Bonomo Júnior (RM565155)\n" +
                                "- Thomas Fontes (RM562254)\n" +
                                "- Matheus Pereira Molina (RM563399)\n")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Maciel")
                                .email("rm562795@fiap.com.br"))
                        .license(new License()
                                .name("Licença Acadêmica FIAP")));
    }
}
