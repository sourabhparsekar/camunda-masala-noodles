package com.noodles.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @implNote <b>Swagger Configuration</b>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Camunda Noodles Service").version("v1.0.0")
                        .description("We will try to cook veg masala instant noodles using Springboot Camunda Workflow.")
                        .termsOfService("Terms of Service").license(getLicense()).contact(getContact()));

    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("sourabhanant@gmail.com");
        contact.setName("Sourabh Parsekar");
        contact.setUrl("https://sourabhparsekar.medium.com/");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }

    private License getLicense() {
        License license = new License();
        license.setName("Apache License, Version 2.0");
        license.setUrl("https://opensource.org/licenses/Apache-2.0");
        license.setExtensions(Collections.emptyMap());
        return license;
    }

}