package com.challenge.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {


    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {


        return new OpenAPI()
                        .info(new Info().title("Central de Erros - Desafio Final - Codenation")
                                        .version(appVersion)
                                        .description(appDesciption)
                                        .termsOfService("http://swagger.io/terms/")
                                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                                        .contact(contato())
                             );
    }

    private Contact contato() {
        Contact contact = new Contact();
        contact.setName("Anderson Antoniny Nascimento");
        contact.email("anderson.an@gmail.com");
        contact.setUrl("https://linkedin.com/in/antoniny");

        return contact;
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder().group("CentralErros_V1").pathsToMatch("/api/v1/**").build();
    }

    // It's important to set it in order to handle Pageable
    static {
        SpringDocUtils.getConfig()
                .replaceWithClass(org.springframework.data.domain.Pageable.class, org.springdoc.core.converters.models.Pageable.class);
    }


}
