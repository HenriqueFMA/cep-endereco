package com.accenture.consumo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI consumoCepOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Consulta CEP")
                        .description("""
                                API que consome a ViaCEP (https://viacep.com.br) via OpenFeign
                                e persiste os endereços consultados no banco H2 em memória.
                                
                                Banco H2: http://localhost:8080/h2-console
                                JDBC URL: jdbc:h2:mem:cepdb
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Accenture")
                                .email("contato@accenture.com")));
    }
}
